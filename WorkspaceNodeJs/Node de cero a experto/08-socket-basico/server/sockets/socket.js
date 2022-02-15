const { io } = require('../server');

/**
 * client: obtiene toda la información de la maquina que se conecta
 */
io.on('connection', (client) => {
    console.log('usuario conectado');


    client.emit('enviarMensaje', {
        usuario: 'Administrator',
        mensaje: 'Bienvenido a está aplicación'
    });


    client.on('disconnect', () => {
        console.log('usuario desconectado');
    });

    // escuchar al cliente, escuchamos el evento enviarMensaje que se encuentra en el HTML
    client.on('enviarMensaje', (data, callback) => {

        console.log(data);

        /**
         * Con broadcast, emitimos mensajes a todos los usuarios conectados
         */
        client.broadcast.emit('enviarMensaje', data);

        /* if (mensaje.usuario) {
            callback({
                resp: 'TODO SALIO BIEN'
            });
        } else {
            callback({
                resp: 'TODO SALIO MAL'
            });
        } */

    });
});