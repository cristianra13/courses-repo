var params = new URLSearchParams(window.location.search);

// referencias de JQuery
var divusuarios = $('#divUsuarios');
var formEnviar = $('#formEnviar');
var txtMensaje = $('#txtMensaje');
var divChatbox = $('#divChatbox');

var nombre = params.get('nombre');
var sala = params.get('sala');


/**
 * Funciones para renderizar usuarios
 */

// recibimos arreglo de personas
function renderizarUsuarios(personas) {
    console.log(personas);
    var html = ''
    html += '<li>';
    html += '<a href="javascript:void(0)" class="active"> Chat de <span> ' + params.get('sala') + '</span></a>';
    html += '</li>';

    for (var i = 0; i < personas.length; i++) {
        html += '<li>';
        html += '<a data-id="' + personas[i].id + '" href="javascript:void(0)"><img src="assets/images/users/1.jpg" alt="user-img" class="img-circle"> <span>' + personas[i].nombre + ' <small class="text-success">online</small></span></a>';
        html += '</li>';
    }

    divusuarios.html(html);

}


/**
 * listeners
 * 
 * Cuando se haga clic en cualquier etiqueta html a, dispara una funcion
 */
divusuarios.on('click', 'a', function() {

    // Este id es el mismo que se agrego anteriormente en la etiqueta a como data-id=""
    var id = $(this).data('id');

    if (id) {
        console.log(id);
    }
});

/**
 * Función para que el scroll se desplace hacía abajo cuando hayan nuevos mensajes
 */
function scrollBottom() {

    // selectors
    var newMessage = divChatbox.children('li:last-child');

    // heights
    var clientHeight = divChatbox.prop('clientHeight');
    var scrollTop = divChatbox.prop('scrollTop');
    var scrollHeight = divChatbox.prop('scrollHeight');
    var newMessageHeight = newMessage.innerHeight();
    var lastMessageHeight = newMessage.prev().innerHeight() || 0;

    if (clientHeight + scrollTop + newMessageHeight + lastMessageHeight >= scrollHeight) {
        divChatbox.scrollTop(scrollHeight);
    }
}

/**
 * yo: variable para saber si el mensaje lo envié yo u otra persona
 * 
 * @param {*} mensaje 
 * @param {*} yo 
 */
function renderizarMensajes(mensaje, yo) {

    var html = '';
    var fecha = new Date(mensaje.fecha);
    var hora = fecha.getHours() + ':' + fecha.getMinutes();
    var adminClass = 'info';

    if (mensaje.nombre === 'Administrador') {

        adminClass = 'danger';
    }

    if (yo) {
        html += '<li class="reverse">';
        html += '<div class="chat-content">';
        html += '<h5>' + mensaje.nombre + '</h5>';
        html += ' <div class="box bg-light-inverse">' + mensaje.mensaje + '</div>';
        html += '</div>';
        html += '<div class="chat-img"><img src="assets/images/users/5.jpg" alt="user" /></div>';
        html += '<div class="chat-time">' + hora + '</div>';
        html += '</li>';
    } else {
        html += '<li class="animated fadeIn">';
        if (mensaje.nombre != 'Administrador') {
            html += '<div class="chat-img"><img src="assets/images/users/1.jpg" alt="user" /></div>';
        }
        html += '<div class="chat-content">';
        html += '<h5>' + mensaje.nombre + '</h5>';
        html += '<div class="box bg-light-' + adminClass + '">' + mensaje.mensaje + '</div>';
        html += '</div>';
        html += '<div class="chat-time">' + hora + '</div>';
        html += '</li>';
    }

    divChatbox.append(html);

}

// disparamos una funcion que dispara un evento
formEnviar.on('submit', function(e) {

    // evita que se recargue la pagina cuando se de enter al enviar un mensaje
    e.preventDefault();

    if (txtMensaje.val().trim().length === 0) {
        return;
    }

    socket.emit('crearMensaje', {
        nombre: nombre,
        mensaje: txtMensaje.val()
    }, function(mensaje) { // acá enviamos un callback
        txtMensaje.val('').focus();
        renderizarMensajes(mensaje, true);
        scrollBottom();
    });

});