require('dotenv').config();
const express = require('express');
const hbs = require('hbs');

const app = express();



// usamos un template ng para renderizar - handlebars
app.set('view engine', 'hbs');
hbs.registerPartials(__dirname + '/views/partials');

// middleware para hacer publica la carpeta public con contenido estatico
app.use(express.static('public'));

/* app.get('/', (req, res) => {
    // renderizamos la vista
    // como segunda opción mandamos las opciones
    res.render('home', {
        nombre: 'Cristian Real',
        titulo: 'Curso de Node'
    });
});

app.get('/generic', (req, res) => {
    res.render('generics', {
        nombre: 'Cristian Real',
        titulo: 'Curso de Node'
    });
});

app.get('/elements', (req, res) => {
    res.render('elements', {
        nombre: 'Cristian Real',
        titulo: 'Curso de Node'
    });
}); */

// ruta comodin
app.get('/', (req, res) => {
    res.sendFile(__dirname + '/public/index.html');
});

app.listen(8000, console.log('Server running on port 8080'));