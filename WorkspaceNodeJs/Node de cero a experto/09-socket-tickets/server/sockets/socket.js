const { io } = require('../server');
const { TicketControl } = require('../classes/ticket-control');

const ticketControl = new TicketControl();


io.on('connection', (client) => {

    client.on('siguienteTicket', (data, callback) => {

        let siguienteTicket = ticketControl.siguienteTicket();

        console.log(siguienteTicket);

        /**
         * Llamamos el callback y mandamos el siguiente ticket que nos da ticketControl
         */
        callback(siguienteTicket);
    });

    // emitir un evento etsadoAtual
    client.emit('estadoActual', {

        actual: ticketControl.getUltimoTicket(),
        ultimosCuatro: ticketControl.getUltimosCuatro()
    });

    // escuchamos el evento atenderTicket
    client.on('atenderTicket', (data, callback) => {

        if (!data.escritorio) {
            return callback({
                err: true,
                message: 'escritorio es obligatorio'
            });
        }

        let atenderTicket = ticketControl.atenderTicket(data.escritorio);

        // retornamos el ticket para que pueda ser trabajado en el front
        callback(atenderTicket);

        // emitir un evento ultimos4 para que cuando se atienda un tocket, se actualice la pantalla publico
        client.broadcast.emit('ultimos4', {

            ultimosCuatro: ticketControl.getUltimosCuatro()
        });
    });



});