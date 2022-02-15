const { response } = require('express');
const jwt = require('jsonwebtoken');
const usuario = require('../models/usuario');

const Usuario = require('../models/usuario');


const validarJWT = async(req, res = response, next) => {

    // obtenemos el header
    const token = req.header('x-token');

    if (!token) {
        return res.status(401).json({
            msg: 'Token invalido'
        });
    }
    try {

        const { uid } = jwt.verify(token, process.env.SECRET_KEY);
        const usuario = await Usuario.findById(uid);

        if (!usuario) {
            return res.status(401).json({
                msg: 'Token invalido - usuario no existe'
            });
        }

        // verificar si el uid esta en estado true
        if (!usuario.estado) {
            return res.status(401).json({
                msg: 'Token invalido - usuario inactivo'
            });
        }

        // asignamos el usuario al request, esto se pasa por referencia por lo que no hacemos return
        req.usuario = usuario;

        next();
    } catch (error) {
        console.log(error);
        res.status(401).json({
            msg: 'Token invalido'
        });
    }
};

module.exports = {
    validarJWT
}