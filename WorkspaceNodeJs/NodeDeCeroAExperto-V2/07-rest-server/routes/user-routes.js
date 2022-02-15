const { Router } = require('express');
const { check } = require('express-validator');
const {
    getUsers,
    postUsers,
    putUsers,
    deleteUsers
} = require('../controllers/user.controller');

// apunta por defecto al archivo index.js
const {
    validarCampos,
    validarJWT,
    tieneRole,
    validarAdminRol
} = require('../middlewares');

const {
    esRolValido,
    esEmailValido,
    existeUsuarioById
} = require('../helpers/db-validators');

const router = Router();


/**
 * mandamos la referencia a la función,
 * creamos la ruta en otro archivo
 */
router.get('/', getUsers);

router.post('/', [
    validarJWT,
    tieneRole('ADMIN_ROLE', 'USER_ROLE'),
    /**
     * le decimos que campo del body vamos a revisar
     * check() va almacenando todos los errores del request para despues confirmarlos en el controller
     */
    check('nombre', 'Nombre es obligatorio').not().isEmpty(),
    check('password', 'Password es obhligatorio y debe ser mayor a 6 caracteres').isLength({ min: 8, max: 22 }),
    check('email', 'Email no cumple con los parámetros').isEmail(),
    check('email', 'Email no cumple con los parámetros').custom(esEmailValido),
    // validamos el rol contrra la BD
    check('role').custom(esRolValido), // no enviamos explicitamente el rol ya que en ES6 si tienen el mismo nombre, ya JS sabe que debe hacer
    validarCampos // con esto ejecutamos la validación de los campos
], postUsers);

router.put('/:id', [
    validarJWT,
    tieneRole('ADMIN_ROLE', 'USER_ROLE'),
    check('id', 'No es un id valido').isMongoId(),
    check('id').custom(existeUsuarioById), // llamamos el validator
    check('role').custom(esRolValido), // no enviamos explicitamente el rol ya que en ES6 si tienen el mismo nombre, ya JS sabe que debe hacer c
    validarCampos
], putUsers);


router.delete('/:id', [
    validarJWT,
    // validarAdminRol,
    tieneRole('ADMIN_ROLE', 'USER_ROLE'),
    check('id', 'No es un id valido').isMongoId(),
    check('id').custom(existeUsuarioById), // llamamos el validator
    validarCampos
], deleteUsers);



module.exports = router;