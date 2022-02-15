const { Router } = require('express');
const { check } = require('express-validator');
const { cargarArchivos, actualizarImagen, mostrarImagen, actualizarImagenCloudinary } = require('../controllers/uploads.controller');
const { coleccionesPermitidas } = require('../helpers/db-validators');
const { validarCampos, validarArchivoSubir } = require('../middlewares');

const router = Router();

router.post('/', [validarArchivoSubir], cargarArchivos);

router.put('/:coleccion/:id', [
    check('id', 'id debe ser valido de mongo').isMongoId(),
    check('coleccion').custom(col => coleccionesPermitidas(col, ['usuarios', 'productos'])),
    validarCampos
], actualizarImagenCloudinary);

router.get('/:coleccion/:id', [
    check('id', 'id debe ser valido de mongo').isMongoId(),
    check('coleccion').custom(col => coleccionesPermitidas(col, ['usuarios', 'productos'])),
    validarCampos
], mostrarImagen);

module.exports = router;