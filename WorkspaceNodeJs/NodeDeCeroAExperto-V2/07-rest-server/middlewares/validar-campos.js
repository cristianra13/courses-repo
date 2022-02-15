const { validationResult } = require("express-validator");

const validarCampos = (req, res, next) => {

    const errors = validationResult(req);

    if (!errors.isEmpty()) {
        return res.status(400).json(errors);
    }

    /**
     * si el middleware pasa, se llama a next
     * para que siga con el siguiente middleware, wsi no hay mas,
     * se prosigue con el controlador
     */
    next();

};

module.exports = {
    validarCampos
};