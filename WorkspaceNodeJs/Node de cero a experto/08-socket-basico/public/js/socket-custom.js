// definimos la funciones que queremos disparar cuando recibamso información del servidor o cuando necesitemos enviar informacion al server

var socket = io();


// .on() sirven para escuchar sucesos

// notifica conexión entre servidor y cliente o viseversa
socket.on('connect', function() {
    // cuando se conecte
    console.log('conectado a server');

});

socket.on('disconnect', function() {

    // cuando se pierde conexión con el server
    console.log('perdida de conexión con server');
});

// emitir mensaje desde cliente hacia servidor
// emmit son para enviar para información
socket.emit('enviarMensaje', {
    usuario: 'Cristian',
    mensaje: 'Hola mundo'
}, function(resp) { // callback
    console.log(resp); // respuesta que llega del server
});

// escuchar al servidor
socket.on('enviarMensaje', function(mensajeServer) {
    console.log('Servidor', mensajeServer);
});