const { io } = require('../server');
const { Usuarios } = require('../classes/usuarios');
const { crearMensaje } = require('../utils/util');


const usuarios = new Usuarios();

io.on('connection', (client) => {

    client.on('entrarChat', (usuario, callback) => {

        if (!usuario.nombre || !usuario.sala) {
            return callback({
                error: true,
                message: 'El nombre y la sala son necesarios'
            });
        }

        // validación para conectar un usuario a una sala de chat
        client.join(usuario.sala);

        /**
         * El objeto client.id es unico
         */
        usuarios.agregarPersona(client.id, usuario.nombre, usuario.sala);


        /**
         * evento que se dispara cada ves que una persona sale o entra al chat
         */
        client.broadcast.to(usuario.sala).emit('listaPersonas', usuarios.getPersonasBySala(usuario.sala));
        client.broadcast.to(usuario.sala).emit('crearMensaje', crearMensaje('Administrador', `${usuario.nombre} se unió`));

        // retornamos
        callback(usuarios.getPersonasBySala(usuario.sala));
    });


    client.on('crearMensaje', (data, callback) => {

        let persona = usuarios.getPeronaById(client.id);

        let mensaje = crearMensaje(persona.nombre, data.mensaje);

        /**
         * mandamos el mensaje de un usuario para todos los demas
         */
        client.broadcast.to(persona.sala).emit('crearMensaje', mensaje);

        callback(mensaje);

    });

    client.on('disconnect', () => {

        let personaBorrada = usuarios.eliminarPersona(client.id);

        client.broadcast.to(personaBorrada.sala).emit('crearMensaje', crearMensaje('Administrador', `${personaBorrada.nombre} salió`));

        client.broadcast.to(personaBorrada.sala).emit('listaPersonas', usuarios.getPersonasBySala(personaBorrada.sala));
    });

    client.on('mensajePrivado', data => {

        let persona = usuarios.getPeronaById(client.id);

        console.log(persona);
        // data.para es la persona  a la que quiero enviar el mensaje
        client.broadcast.to(data.para).emit('mensajePrivado', crearMensaje(persona.nombre, data.mensaje));
    });

});