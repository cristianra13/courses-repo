// toda la configuracion del sockets con el servidor

// referencias HTML
const lblOnline = document.querySelector('#lblOnline');
const lblOffline = document.querySelector('#lblOffline');
const txtMensaje = document.querySelector('#txtMensaje');
const btnEnviar = document.querySelector('#btnEnviar');

const socketClient = io();

// listeners -> on es para escuchar un evento, detectamos cambios de conexión o desconexión del servidor
socketClient.on('connect', () => {
    lblOffline.style.display = 'none';
    lblOnline.style.display = '';
});

socketClient.on('disconnect', () => {
    console.log('Disconnected');
    lblOnline.style.display = 'none';
    lblOffline.style.display = '';
});

// escuchamos evento enviar-mensaje que llega desde el server
socketClient.on('enviar-mensaje', (payload) => {
    console.log(payload);
});

btnEnviar.addEventListener('click', () => {
    const mensaje = txtMensaje.value;
    const payload = {
        mensaje,
        id: '32857984375uhj436',
        socketId: socketClient.id,
        fecha: new Date().getTime()
    };
    // con emit emitimos un evento, primero el nombre del evento y luego el valor, tercer argumento un callback
    socketClient.emit('enviar-mensaje', payload, (id) => {
        console.log('Desde el server', id);

    });

});