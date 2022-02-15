require('./config/config')

const express = require('express')
const app = express()
const mongoose = require('mongoose')

const bodyParser = require('body-parser')
const { response } = require('express')
const path = require('path')

/**
 * Cuando se vea algo como app.use
 * estos son middlewares, las cuales son funciones que
 * se vana disparar cada vez que el código pase por aquí 
 */

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))

// parse application/json
app.use(bodyParser.json())

// Habilitar la carpeta public
//app.use(express.static(path.resolve(__dirname, '../public')));
app.use(express.static(__dirname + '/public'));


/**
 * Refrerencia al index.js donde tenemos nuestras rutas
 * configuracion global de rutas
 */
app.use(require('./routes/index'));


/**
 * Creación de conexión a base de datos de mongo
 */

mongoose.connect(process.env.URLDB, { useNewUrlParser: true, useCreateIndex: true },
    (err, resp) => {

        if (err) {
            throw new err;
        }

        console.log(process.env.URLDB);
        console.log('Base de datos Online');
    });

app.listen(process.env.PORT, () => {
    console.log('App init port:', process.env.PORT);
})