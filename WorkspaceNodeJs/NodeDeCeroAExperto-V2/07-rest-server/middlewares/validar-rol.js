const { response } = require("express")

const validarAdminRol = (req, res = response, next) => {

    if (!req.usuario) {
        return res.status(500).json({
            msg: 'Se quiere validar rol sin validar primero el token'
        });
    }

    const { role, nombre } = req.usuario;
    console.log(role, nombre);

    if (role !== 'ADMIN_ROLE') {
        return res.status(401).json({
            msg: `${nombre} no tiene posee permisos suficientes para está acción`
        });
    }
    next();
}

const tieneRole = (...roles) => {

    return (req, res = response, next) => {

        if (!req.usuario) {
            return res.status(500).json({
                msg: 'Se quiere validar rol sin validar primero el token'
            });
        }

        if (!roles.includes(req.usuario.role)) {
            return res.status(401).json({
                msg: `Rol no permitido para está acción - ${req.usuario.role}`
            });
        }
        next();
    };

};

module.exports = {
    validarAdminRol,
    tieneRole
}