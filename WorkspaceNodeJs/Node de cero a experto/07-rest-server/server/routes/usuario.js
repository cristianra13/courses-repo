const express = require('express');
const Usuario = require('../models/usuario');
const bcrypt = require('bcrypt');
const _ = require('underscore');
const { response } = require('express');

const { verificaToken, verificaAdminRole } = require('../middlewares/autenticacion')

const app = express();


/**
 * Tipos de peticiones GET, POST, PUT, DELETE
 */

app.get('/usuario', verificaToken, (req, res) => {

    // si viene la variable desde, quiere decir que se toma desde el valor que agrego el usuario, si no, desde la pagina 0
    let desde = req.query.desde || 0;
    desde = Number(desde);

    let limite = req.query.limite || 5;
    limite = Number(limite);

    Usuario.find({ estado: true }, 'nombre email role estado google')
        .skip(desde) // paginado se salta los primeros 5 para mostrar los sif¿guientes
        .limit(limite) // paginado de 5 en 5
        .exec((err, usuarios) => {
            if (err) {
                return res.status(400).json({
                    ok: false,
                    err
                });
            }

            Usuario.count({ estado: true }, (err, conteo) => {
                res.json({
                    ok: true,
                    usuarios,
                    cant: conteo
                })
            });


        })
})

app.post('/usuario', [verificaToken, verificaAdminRole], (req, res) => {

    let body = req.body;

    // creamos una instancia de usuarioSchema
    let usuario = new Usuario({
        nombre: body.nombre,
        password: bcrypt.hashSync(body.password, 10), // hashSync no utiliza callbacks
        email: body.email,
        role: body.role
    });

    // usuarioDB es la respuesta del usuario que se guardo en BD
    usuario.save((err, usuarioDB) => {
        if (err) {
            return res.status(400).json({
                ok: false,
                err
            });
        }

        res.json({
            ok: true,
            usuario: usuarioDB
        });
    });
})

app.put('/usuario/:id', [verificaToken, verificaAdminRole], function(req, res) {
    /**
     *  Obtenemos el id del usuario
     * el nombre que este en el path, debe ser el mismo que se toma de los params
     */
    let id = req.params.id;

    /**
     * con _.pick() queremos que solo actualice los atrubutos que necesitemos
     * en este caso mandamos primero el objeto a actualizar y como segundo parametro,
     * un arreglo con los atributos que solo se permiten actualizar
     */
    let body = _.pick(req.body, ['nombre', 'email', 'img', 'role', 'estado']);


    /**
     * primero pasamos el id a buscar, 
     * luego el objeto a actualizar 
     * como tercer argumento, podemos mandar los options el cual es un objeto, (Validaro docs de mongoose)
     * en este caso { new: true } para que nos devuelva el objeto ya actualizado
     * 
     * por último, el callback
     */
    Usuario.findByIdAndUpdate(id, body, { new: true, runValidators: true }, (err, usuarioDB) => {

        if (err) {
            return res.status(400).json({
                ok: false,
                err
            });
        }

        res.json({
            ok: true,
            usuario: usuarioDB
        })

    })


})

app.delete('/usuario/:id', [verificaToken, verificaAdminRole], function(req, res) {

    let id = req.params.id;
    let cambiaEstado = {
        estado: false
    }

    // Usuario.findByIdAndRemove(id, (err, usuarioEliminado) => {
    Usuario.findByIdAndUpdate(id, cambiaEstado, { new: true }, (err, usuarioEliminado) => {
        if (err) {
            return res.status(400).json({
                ok: false,
                err
            });
        }

        if (!usuarioEliminado) {
            return res.status(400).json({
                ok: false,
                err: {
                    message: 'Usuario no encontrado'
                }
            });
        }

        res.json({
            ok: true,
            usuario: usuarioEliminado
        });
    })
})

module.exports = app;