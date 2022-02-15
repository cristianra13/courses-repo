const { Router } = require('express');
const { check } = require('express-validator');

const { crearCategoria, obtenerCategorias, obtenerCategoria, actualizarCategoria, eliminarCategoria } = require('../controllers/categorias-controller');
const { existeCategoriaById } = require('../helpers/db-validators');
const { validarJWT, validarCampos, validarAdminRol } = require('../middlewares');

const router = Router();


// Obtener todas las categorias
router.get('/', obtenerCategorias);

router.get('/:id', [
    check('id', 'No es un id valido').isMongoId(),
    check('id').custom(existeCategoriaById),
    validarCampos
], obtenerCategoria);

// método para cualquieracon token valido
router.post('/', [
    validarJWT,
    check('nombre', 'nombre es obligatorio').not().isEmpty(),
    validarCampos
], crearCategoria);

// método para cualquieracon token valido
router.put('/:id', [
    validarJWT,
    check('id', 'No es un id valido').isMongoId(),
    check('id').custom(existeCategoriaById),
    check('nombre', 'nombre es obligatorio').not().isEmpty(),
    validarCampos
], actualizarCategoria);

// método para cualquieracon token valido
router.delete('/:id', [
    validarJWT,
    check('id', 'No es un id valido').isMongoId(),
    check('id').custom(existeCategoriaById),
    validarAdminRol,
    validarCampos
], eliminarCategoria);


module.exports = router;