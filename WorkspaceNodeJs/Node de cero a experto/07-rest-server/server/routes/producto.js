const express = require('express');
const { verificaToken } = require('../middlewares/autenticacion');
const categoria = require('../models/categoria');

let app = express();
let Producto = require('../models/producto');


/**
 * Obtener todos los productos
 */
app.get('/productos', verificaToken, (req, resp) => {
    // trae todos los productos
    //populate usuario y categoria
    // paginado

    let desde = req.query.desde || 0;
    desde = Number(desde);

    let limite = req.query.limite || 5;
    limite = Number(limite);

    Producto.find({ disponible: true })
        .skip(desde)
        .limit(limite)
        .populate('usuario', 'nombre email')
        .populate('categoria', 'descripcion')
        .exec((err, productos) => {
            if (err) {
                return resp.status(500).json({
                    ok: false,
                    err
                });
            }

            Producto.count({ disponible: true }, (err, conteo) => {
                resp.json({
                    ok: true,
                    productos,
                    cant: conteo
                });
            });


        });

});

/**
 * Obtener un producto por id
 */
app.get('/productos/:id', verificaToken, (req, resp) => {
    //populate usuario y categoria

    let id = req.params.id;

    Producto.findById(id, { disponible: true })
        .populate('usuario', 'nombre email')
        .populate('categoria', 'descripcion')
        .exec((err, productoDB) => {
            if (err) {
                return resp.status(500).json({
                    ok: false,
                    err
                });
            }

            if (!productoDB) {
                return resp.status(500).json({
                    ok: false,
                    err: {
                        message: 'Id no existe'
                    }
                });
            }

            resp.json({
                ok: true,
                producto: productoDB
            });
        });


});

/**
 * Buscar productos
 */
app.get('/productos/buscar/:termino', verificaToken, (req, resp) => {

    let termino = req.params.termino;

    /**
     * Creamos un regex de termino y con
     * 'i' decimos que sea insensible a minuscula y mayusculas
     */
    let regex = new RegExp(termino, 'i');


    Producto.find({ nombre: regex })
        .populate('categoria', 'nombre')
        .exec((err, productos) => {
            if (err) {
                resp.status(500).json({
                    ok: false,
                    err
                });
            }

            resp.json({
                ok: true,
                productos
            });

        });
});


/**
 * Crear un producto
 */
app.post('/productos', verificaToken, (req, resp) => {
    // grabar usuario
    // grabar categoria del listado de categorias}

    let body = req.body;

    let producto = new Producto({
        usuario: req.usuario._id,
        nombre: body.nombre,
        precioUni: body.precioUni,
        descripcion: body.descripcion,
        disponible: body.disponible,
        categoria: body.categoria
    });

    producto.save((err, productoDB) => {

        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        resp.status(201).json({
            ok: true,
            producto: productoDB
        })
    });
});

/**
 * Actualizar un producto
 */
app.put('/productos/:id', verificaToken, (req, resp) => {

    let id = req.params.id;
    let body = req.body;

    Producto.findById(id, (err, productoDB) => {
        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!productoDB) {
            return resp.status(400).json({
                ok: false,
                err
            });
        }

        productoDB.nombre = body.nombre;
        productoDB.precioUni = body.precioUni;
        productoDB.categoria = body.categoria;
        productoDB.disponible = body.disponible;
        productoDB.descripcion = body.descripcion;


        productoDB.save((err, productoSave) => {
            if (err) {
                return resp.status(500).json({
                    ok: false,
                    err
                });
            }

            resp.json({
                ok: true,
                producto: productoSave
            })
        });
    });

});

/**
 * Borrar un producto
 */
app.delete('/productos/:id', (req, resp) => {
    //borrado logico - campo disponible = false

    let id = req.params.id;

    Producto.findById(id, (err, productoDB) => {
        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!productoDB) {
            return resp.status(400).json({
                ok: false,
                err: {
                    message: 'Id no existe'
                }
            });
        }

        productoDB.disponible = false;

        productoDB.save((err, productoBorrado) => {
            if (err) {
                return resp.status(500).json({
                    ok: false,
                    err
                });
            }

            resp.json({
                ok: true,
                producto: productoBorrado,
                message: 'Producto borrado satisfactoriamente'
            });
        });
    });


});


module.exports = app;