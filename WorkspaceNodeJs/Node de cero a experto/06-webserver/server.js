const express = require('express')
const app = express()
const hbs = require('hbs');
require('./hbs/helpers')

/**
 * Le decimos a Heroku que tome un puerto cualquiera,
 * en cqaso contrario, ponga el puerto 3000
 */
const port = process.env.PORT || 3000;

/**
 * Creamos un middleware:
 * es una instrucción o un callback siempre que 
 * no importa que url, sea llamada o se pida
 * 
 * Acá estariamos llamando el index.html para mosrtrar
 * 
 * Tener cuidado ya que esto se llama por un get, por lo que su hay algún servicio get
 * puede chocar con el llamado del html
 */

app.use(express.static(__dirname + '/public'))

// express  HBS Engine
hbs.registerPartials(__dirname + '/views/partials/'); // directorio que contiene los parciales
app.set('view engine', 'hbs');

app.get('/', (req, res) => {
    // con send mandamos la salida, por defecto la manda en formato json
    // res.send(salida)

    /**
     * Con esto se renderiza primero el archivo home.hbs y posterior,
     * un objeto con dos variables las cuales está esperando el archivo hbs
     */
    res.render('home', {
        nombre: 'Cristian'
    })
})

app.get('/about', (req, res) => {

    res.render('about', {
        nombre: 'Sin señal en este momento'
    })
})

app.listen(port)