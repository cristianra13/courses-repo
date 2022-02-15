const { Router } = require('express');
const { check } = require('express-validator');
const { obtenerProductos, crearProducto, actualizarProducto, eliminarProducto, obtenerProducto } = require('../controllers/productos.controller');
const { existeProductoById, existeCategoriaById } = require('../helpers/db-validators');
const { validarJWT, validarCampos, validarAdminRol } = require('../middlewares');

const app = Router();

app.get('/', obtenerProductos);

app.get('/:id', [
    check('id', 'No es un id valido').isMongoId(),
    check('id', 'id es obligatorio').custom(existeProductoById),
    validarCampos
], obtenerProducto);

app.post('/', [
    validarJWT,
    check('nombre', 'nombre es obligatorio').not().isEmpty(),
    check('usuario', 'usuario es obligatorio').not().isEmpty(),
    check('categoria', 'Categoria NO es un id de mongo').isMongoId(),
    check('categoria').custom(existeCategoriaById),
    validarCampos
], crearProducto);

app.put('/:id', [
    validarJWT,
    check('id', 'id es obligatorio').custom(existeProductoById),
    check('id').isMongoId(),
    validarCampos
], actualizarProducto);

app.delete('/:id', [
    validarJWT,
    check('id', 'No es un id valido').isMongoId(),
    check('id').custom(existeProductoById),
    validarAdminRol,
    validarCampos
], eliminarProducto);

module.exports = app;