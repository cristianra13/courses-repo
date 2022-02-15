const socketController = (socket) => {

    console.log('Cliente conectado', socket.id);

    socket.on('disconnect', () => {
        console.log('Client Disconnected', socket.id);
    });

    /**
     * escuchamos un evento del cliente
     * El priumer argumento generalmente es el payload
     */
    socket.on('enviar-mensaje', (payload, callback) => {

        const id = 123456;
        /**
         * este callback se ejecuta con lo que llega desde el cliente, 
         * esto sirve para notificar al cliente sobre algún proceso 
         * en ejecución o ejecutado
         */
        callback(id);

        // mandar un mensaje a todos los clientes conectados desde el server
        // broadcast nos permite enviar el mensaje a todos los conectados
        socket.broadcast.emit('enviar-mensaje', payload);

    });

}

module.exports = {
    socketController
}