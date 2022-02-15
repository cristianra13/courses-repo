const express = require('express')
const app = express()


/**
 * Agregamos las rutas que vamos a usar
 */
app.use(require('./usuario'));
app.use(require('./categoria'));
app.use(require('./producto'));
app.use(require('./upload'));

// agregamos la ruta para login
app.use(require('./login'));
app.use(require('./imagenes'));


module.exports = app;