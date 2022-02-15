const existeCategoria = (req, res, next) => {
    if (!req.params.id) {
        res.status(400).json({
            msg: 'Id no es valido'
        });
    }
}