const express = require('express');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken')

// imports google
const { OAuth2Client } = require('google-auth-library');
const client = new OAuth2Client(process.env.CLIENT_ID);

const Usuario = require('../models/usuario');


const app = express();


app.post('/login', (req, resp) => {

    let body = req.body;

    // esta es la primera condicion, que el email exista
    Usuario.findOne({ email: body.email }, (err, usuarioDB) => {

        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        if (!usuarioDB) {
            return resp.status(400).json({
                ok: false,
                err: {
                    message: 'Usuario o contraseña invalidos'
                }
            });
        }

        /**
         * Comparamos las dos contraseñas
         */
        if (!bcrypt.compareSync(body.password, usuarioDB.password)) {
            return resp.status(400).json({
                ok: false,
                err: {
                    message: 'Usuario o contraseña invalidos'
                }
            });
        }

        let token = jwt.sign({
            usuario: usuarioDB, // payload del token

        }, process.env.SEED, { expiresIn: process.env.EXP_DATE }); // son segundos por minutos = 1 hora

        resp.json({
            ok: true,
            usuario: usuarioDB, // payload del token
            token
        })

    });

});


/**
 * Configuraciones de Google
 */
async function verify(token) {

    const ticket = await client.verifyIdToken({
        idToken: token,
        audience: process.env.CLIENT_ID, // Specify the CLIENT_ID of the app that accesses the backend
        // Or, if multiple clients access the backend:
        //[CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3]
    });

    // dentro de payload esta toda la información del usuario
    const payload = ticket.getPayload();

    console.log(payload.name);
    console.log(payload.email);
    console.log(payload.picture);

    return {
        nombre: payload.name,
        email: payload.email,
        img: payload.picture,
        google: true
    }
}


/**
 * path redireccionamiento para logueo con google
 */
app.post('/google', async(req, resp) => {

    /**
     * Obtenemos el idtoken de google
     */
    let token = req.body.idtoken;

    // googleUser contiene toda la información del usuario
    let googleUser = await verify(token)
        .catch(err => {
            return resp.status(403).json({
                ok: false,
                err
            });
        });

    // buscamos en nuestra base de datos si existe un usuario con ese email
    Usuario.findOne({ email: googleUser.email }, (err, usuarioDB) => {

        if (err) {
            return resp.status(500).json({
                ok: false,
                err
            });
        }

        // si el usuario existe en nuestra base de datos
        if (usuarioDB) {
            // y no se ha autenticado con Google, quiere decir que uso una autenticación por la apolicación nuestra
            if (!usuarioDB.google) {
                // no permitimos el login por Google
                return resp.status(400).json({
                    ok: false,
                    err: {
                        message: 'Usuario ya autenticado con credenciales propias'
                    }
                });
            } else {
                // Usuario que se ha autenticado por Google
                let token = jwt.sign({
                    usuario: usuarioDB, // payload del token

                }, process.env.SEED, { expiresIn: process.env.EXP_DATE });

                return resp.json({
                    ok: true,
                    usuario: usuarioDB,
                    token
                });
            }
        } else {
            // Si el usuario no existe en BD nuestra, es la primera vez que se está autenticando
            let usuario = new Usuario();
            usuario.nombre = googleUser.nombre;
            usuario.email = googleUser.email;
            usuario.img = googleUser.img;
            usuario.google = true;
            usuario.password = 'default_password';

            usuario.save((err, usuarioDB) => {
                if (err) {
                    return resp.status(500).json({
                        ok: false,
                        err
                    });
                }

                let token = jwt.sign({
                    usuario: usuarioDB, // payload del token

                }, process.env.SEED, { expiresIn: process.env.EXP_DATE });

                return resp.json({
                    ok: true,
                    usuario: usuarioDB,
                    token
                });

            });
        }

    });
})

module.exports = app;