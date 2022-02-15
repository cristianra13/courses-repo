// comando para establecer la comunicación o coinexión
var socket = io();


/**
 * Parametros opcionales que llegan por la url
 */
var searchParams = new URLSearchParams(window.location.search);

if (!searchParams.has('escritorio')) {
    // si no existe escritorio en la url, salimos de la pantalla
    window.location = 'index.html';
    throw new Error('El escritorio es necesario');
}

var escritorio = searchParams.get('escritorio');

$('h1').text('Escritorio ' + escritorio);
var labelSmall = $('small');

$('button').on('click', function() {

    var mensaje = 'No hay tickets pendientes';
    /**
     * emitimos el evento atenderTicker
     */
    socket.emit('atenderTicket', { escritorio: escritorio }, function(resp) {

        if (resp === mensaje) {
            labelSmall.text(resp);
            alert(mensaje);
            return;
        }

        labelSmall.text('Ticket ' + resp.numeroTicket);
    });

});