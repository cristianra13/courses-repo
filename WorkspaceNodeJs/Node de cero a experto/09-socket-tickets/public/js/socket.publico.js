// comando para establecer la comunicación o coinexión
var socket = io();

var labelTicket1 = $('lblTicket1');
var labelTicket2 = $('lblTicket2');
var labelTicket3 = $('lblTicket3');
var labelTicket4 = $('lblTicket4');

var lblEscritorio1 = $('lblEscritorio1');
var lblEscritorio2 = $('lblEscritorio2');
var lblEscritorio3 = $('lblEscritorio3');
var lblEscritorio4 = $('lblEscritorio4');

var labelTickets = [labelTicket1, labelTicket2, labelTicket3, labelTicket4];
var labelEscritorios = [lblEscritorio1, lblEscritorio2, lblEscritorio3, lblEscritorio4];

socket.on('estadoActual', function(data) {
    actualizaHtml(data);
});

socket.on('ultimos4', function(data) {

    var audio = new Audio('audio/new-ticket.mp3');
    audio.muted = true;
    audio.play();
    audio.muted = false;

    actualizaHtml(data);
});

/**
 * Actualizamos el html ya que lo debemos llamar varias veces
 */
function actualizaHtml(data) {

    data.ultimosCuatro.forEach((element, index) => {
        $(`#lblTicket${index+1}`).text("Ticket " + element.numeroTicket);
        $(`#lblEscritorio${index+1}`).text("Escritorio " + element.escritorio);
    });

    /* for (var i = 0; i <= ultimosCuatro.length - 1; i++) {

        labelTickets[i].text('Ticket ' + ultimosCuatro[i].numeroTicket);
        labelEscritorios[i].text('Escritorio ' + ultimosCuatro[i].escritorio);
    } */
}