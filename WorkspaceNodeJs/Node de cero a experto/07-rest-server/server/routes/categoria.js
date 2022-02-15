const express = require('express');

let { verificaToken, verificaAdminRole } = require('../middlewares/autenticacion');
const _ = require('underscore');
let app = express();

let Categoria = require('../models/categoria');

/**
 * Se encarga de mostrar todas las categorias
 */
app.get('/categoria', verificaToken, (req, resp) => {
    // aparezcan todas las categorias
    Categoria.find()
        .sort('descripcion')
        .populate('usuario', 'nombre email') // revisa que ObjectsId existen dentro de categoria y permite cargar información
        .exec((err, categorias) => {

            if (err) {
                return resp.status(500).json({
                    ok: false,
                    err
                });
            }

            resp.json({
                ok: true,
                categorias
            });
        })
});

/**
 * Mostrar categoria por id
 */
app.get('/categoria/:id', verificaToken, (req, resp) => {
    // Categoria.findById()

    let id = req.params.id;

    Categoria.findById(id)
        .exec((err, categoriaDB) => {

            if (err) {
                return resp.status(500).json({
                    ok: false,
                    err
                });
            }

            if (!categoriaDB) {
                return resp.status(400).json({
                    ok: false,
                    err: {
                        message: 'Categoria no encontrada'
                    }
                });
            }

            resp.json({
                ok: true,
                categoria: categoriaDB
            });

        });
});

/**
 * Crear nueva categoria
 */
app.post('/categoria', verificaToken, (req, resp) => {
    // regresa la nueva categoria
    //req.usuario._id
    let body = req.body;

    let categoria = new Categoria({
        descripcion: body.descripcion,
        usuario: req.usuario._id
    });

    categoria.save((err, categoriaDB) => {
        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            })
        }

        // si no se creo la categoria
        if (!categoriaDB) {
            return resp.status(400).json({
                ok: false,
                err
            })
        }

        resp.json({
            ok: true,
            categoria: categoriaDB
        });
    });
});

/**
 * actualiza descripcion categoria
 */
app.put('/categoria/:id', verificaToken, (req, resp) => {

    let id = req.params.id;

    let body = req.body;

    let descCategoria = {
        descripcion: body.descripcion
    }

    /**
     * primero pasamos el id a buscar, 
     * luego el objeto a actualizar 
     * como tercer argumento, podemos mandar los options el cual es un objeto, (Validaro docs de mongoose)
     * en este caso { new: true } para que nos devuelva el objeto ya actualizado
     * 
     * por último, el callback
     */
    Categoria.findByIdAndUpdate(id, descCategoria, { new: true, runValidators: true }, (err, categoriaDB) => {
        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!categoriaDB) {
            return resp.status(400).json({
                ok: false,
                err
            });
        }

        resp.json({
            ok: true,
            categoria: categoriaDB
        });
    });

});

/**
 * 
 */
app.delete('/categoria/:id', [verificaToken, verificaAdminRole], (req, resp) => {
    // solo el admin puede borrar categoria
    // Categoria.fiindByIdAndRemove

    let id = req.params.id;

    console.log(id);

    Categoria.findByIdAndRemove(id, (err, categoriaEliminada) => {
        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!categoriaEliminada) {
            return res.status(400).json({
                ok: false,
                err: {
                    message: 'Categoria no encontradoa'
                }
            });
        }

        resp.json({
            ok: true,
            message: 'categoria eliminada'
        });
    });
});


module.exports = app;