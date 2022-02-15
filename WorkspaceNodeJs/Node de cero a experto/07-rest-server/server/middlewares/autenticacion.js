const jwt = require('jsonwebtoken');

/**
 * verificación de token por middleware
 */

let verificaToken = (req, res, next) => {

    // tomo el token de los headers
    let token = req.get('token');

    jwt.verify(token, process.env.SEED, (err, payload) => {
        if (err) {
            return res.status(401).json({
                ok: false,
                err
            });
        }

        // obtenemos el usuario del payload
        req.usuario = payload.usuario;

        // se llama el next() para quer continue con la ejecución de resto de código
        next();
    });
};

/**
 * Validar role de administrador - ADMIN_ROLE
 */
let verificaAdminRole = (req, res, next) => {

    let usuario = req.usuario;

    if (usuario.role != 'ADMIN_ROLE') {
        return res.status(403).json({
            ok: false,
            err: {
                message: 'Acción no permitida para el usuario'
            }
        });
    }

    next();

}

/**
 * Validar token por url
 */
let verificaTokenImg = (req, res, next) => {

    let token = req.query.token;

    jwt.verify(token, process.env.SEED, (err, payload) => {
        if (err) {
            return res.status(401).json({
                ok: false,
                err: {
                    message: 'Token invalido!'
                }
            });
        }

        // obtenemos el usuario del payload
        req.usuario = payload.usuario;

        // se llama el next() para quer continue con la ejecución de resto de código
        next();
    });
}


module.exports = {
    verificaToken,
    verificaAdminRole,
    verificaTokenImg
};