const express = require('express');
const { body, validationResult } = require('express-validator');

const app = express();

app.use(express.json());
app.set('view engine', 'ejs');
app.use(express.urlencoded({extended: true}));


app.get('/', (req, res) => {
    res.render('index.ejs');
});

app.post('/registrar', [
    body('txtNombre', 'Ingrese nombre y apellido completo')
    .exists()
    .isLength({min: 5}),

    body('txtEmail', 'Ingrese un email valido')
    .exists()
    .isEmail(),

    body('txtEdad', 'Ingrese un valor númerico')
    .exists()
    .isNumeric()
], (req, res) => {
    // const errors = validationResult(req);
    // if (!errors.isEmpty()) {
    //     res.status(400).json({errors: errors.array()});
    //     console.log(errors)
    // }

    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        console.log(req.body);
        const valores = req.body;
        const validaciones = errors.array();
        // Mandamos los errores a la página
        res.render('index', ({ validaciones: validaciones, valores: valores }));
    } else {
        res.send('!Validación exitosa¡');
    }
        
});

const PORT = 3000;
app.listen(PORT, () => console.log(`Server on port ${PORT}`));
