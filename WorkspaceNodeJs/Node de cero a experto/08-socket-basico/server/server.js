const express = require('express');
const socketIO = require('socket.io');
const http = require('http');

const path = require('path');

const app = express();

/**
 * Express está vasado en http, por lo que son muy parecidos y cuando
 * se usa express, se toman funciones que se usan en http
 */
let server = http.createServer(app);

const publicPath = path.resolve(__dirname, '../public');
const port = process.env.PORT || 3000;

/**
 * Habilitamos la carpeta publica para que todo mundo pueda acceder a ella
 * 
 * Exportamos la variable para poderla usar en otro modulo
 */
app.use(express.static(publicPath));

/**
 * inicializamos socketIO
 * IO = esta es la comunicación del backend
 */
module.exports.io = socketIO(server);
// Usamos el modulo
require('./sockets/socket');




server.listen(port, (err) => {

    if (err) throw new Error(err);

    console.log(`Servidor corriendo en puerto ${ port }`);

});