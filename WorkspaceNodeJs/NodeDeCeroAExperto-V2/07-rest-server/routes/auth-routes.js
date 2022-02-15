const { Router } = require('express');
const { check } = require('express-validator');

const { login, googleSignIn } = require('../controllers/auth-controller');
const { validarCampos } = require('../middlewares/validar-campos');

const router = Router();


router.post('/login', [
    check('email', 'email es obligattorio').isEmail(),
    check('password', 'password es obligattorio').not().isEmpty(),
    validarCampos
], login);

router.post('/google-login', [
    check('id_token', 'id_token es obligatorio'),
    validarCampos
], googleSignIn);

module.exports = router;