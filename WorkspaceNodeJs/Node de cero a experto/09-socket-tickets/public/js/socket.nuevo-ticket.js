// comando para establecer la comunicación o coinexión
var socket = io();

// Hacemos referencia al label para poder usarlo y mostrar el ticket siguiente
var labelNuevoTicket = $('#lblNuevoTicket');

socket.on('connect', function() {
    console.log('conectado');
});

socket.on('disconnect', function() {
    console.log('desconectado');
});

/**
 * escuchamos con on() y recibimos una función cuando se emita el estadoActual
 */
socket.on('estadoActual', function(respuesta) {

    // recibimos el objeto que se devuelve en el evento estadoActual desde socket.js
    labelNuevoTicket.text(respuesta.actual);
});

/**
 * Todos los botones de la pantalla al hacer clic lanzan este evento
 */
$('button').on('click', function() {

    // con null le decimos que no mandamos nada, pero si ejecutamos una función
    socket.emit('siguienteTicket', null, function(siguienteTicket) {

        labelNuevoTicket.text(siguienteTicket);

    });
});