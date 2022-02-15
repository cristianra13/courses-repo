// middlewares
const validarCampos = require('../middlewares/validar-campos')
const validarJWT = require('../middlewares/validar-jwt');
const validarRoles = require('../middlewares/validar-rol');
const validarArchivoSubir = require('../middlewares/validar-archivo');

module.exports = {
    // exportamos todo lo que tenga cada archivo
    ...validarCampos,
    ...validarJWT,
    ...validarRoles,
    ...validarArchivoSubir
}