const { response } = require('express');
const express = require('express');
const fileUpload = require('express-fileupload');
const app = express();

// hacemos el import del schema para guardar en la BD
const Usuario = require('../models/usuario');
const Producto = require('../models/producto');

const fs = require('fs');
const path = require('path');

// default options
app.use(fileUpload());

/**
 * El tipo puede ser usuario o producto
 * el id es para saber que usuario o tipo de producto vamos a actualizar
 */
app.put('/upload/:tipo/:id', (req, res) => {

    let tipo = req.params.tipo;
    let id = req.params.id;

    // si nno hay archivos
    if (!req.files || Object.keys(req.files).length === 0) {
        return res.status(400).json({
            ok: false,
            err: {
                message: 'No se ha seleccionado ningún archivo'
            }
        });
    }

    // validar tipo
    let tiposValidos = ['productos', 'usuarios'];

    if (tiposValidos.indexOf(tipo) < 0) {
        return res.status(400).json({
            ok: false,
            err: {
                message: 'Solo se permiten tipos: ' + tiposValidos.join(', '),
                tipo
            }
        });
    }


    // The name of the input field (i.e. "archivo") is used to retrieve the uploaded file
    let archivo = req.files.archivo;
    let nombreArchivo = archivo.name.split('.');
    let extension = nombreArchivo[nombreArchivo.length - 1];

    // restricción extensiones permitidas
    let extensionesValidas = ['png', 'jpg', 'gif', 'jpeg'];

    // si es menor a cero, quiere decir que no encontro la extension
    if (extensionesValidas.indexOf(extension) < 0) {
        return res.status(400).json({
            ok: false,
            err: {
                message: 'Solo se permiten archivos ' + extensionesValidas.join(', ')
            },
            ext: extension
        });
    }

    // cambiamos el nombre del archivo
    let nombreArchivoNuevo = `${id}-${ new Date().getMilliseconds() }.${extension}`;


    // IMAGEN CARGADA CON EXITO
    archivo.mv(`uploads/${tipo}/${nombreArchivoNuevo}`, (err) => {

        if (err) {
            return res.status(500).json({
                ok: false,
                err
            });
        }

        // Acá ya sabemos que la imagen se cargo
        if (tipo === 'usuarios')
            imagenUsuario(id, res, nombreArchivoNuevo);
        else
            imagenProducto(id, res, nombreArchivoNuevo);

        /* res.json({
            ok: true,
            message: 'Imagen cargada exitosamente!'
        }); */
    });

});

function imagenUsuario(id, resp, nombreArchivo) {

    Usuario.findById(id, (err, usuarioDB) => {

        if (err) {
            borraArchivo(nombreArchivo, 'usuarios');
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!usuarioDB) {
            borraArchivo(nombreArchivo, 'usuarios');
            return resp.status(400).json({
                ok: false,
                err: {
                    message: 'Usuario no existe'
                }
            });
        }

        /**
         * Borramos la imagen del usuario si este ya tiene una,
         * si no, se carga la imagen
         */
        borraArchivo(usuarioDB.img, 'usuarios');

        usuarioDB.img = nombreArchivo;
        usuarioDB.save((err, usuarioDB) => {
            resp.json({
                ok: true,
                usuario: usuarioDB,
                img: nombreArchivo
            });
        });

    });
}

function imagenProducto(id, resp, nombreArchivo) {

    Producto.findById(id, (err, productoDB) => {

        if (err) {
            borraArchivo(nombreArchivo, 'productos');
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!productoDB) {
            borraArchivo(nombreArchivo, 'productos');
            return resp.status(400).json({
                ok: false,
                err: {
                    message: 'Producto no existe'
                }
            });
        }

        /**
         * Borramos la imagen del usuario si este ya tiene una,
         * si no, se carga la imagen
         */
        borraArchivo(productoDB.img, 'productos');

        productoDB.img = nombreArchivo;
        productoDB.save((err, productoGuardado) => {
            resp.json({
                ok: true,
                producto: productoGuardado,
                img: nombreArchivo
            });
        });

    });
}


function borraArchivo(nombreImagen, tipo) {
    // verificamos que la ruta de la imagen exista
    let pathImagen = path.resolve(__dirname, `../../uploads/${tipo}/${ nombreImagen }`);

    // confirmamos que el path exista, esto para no duplicar la imagen del usuario
    if (fs.existsSync(pathImagen)) {
        // en caso de que exista, debemos borrar ese path
        fs.unlinkSync(pathImagen);
    }
}

module.exports = app;