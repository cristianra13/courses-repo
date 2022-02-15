const { Schema, model } = require('mongoose');


const UsuarioSchema = Schema({
    nombre: {
        type: String,
        required: [true, 'Nombre es obligatorio']
    },
    email: {
        type: String,
        required: [true, 'Email es obligatorio'],
        unique: true
    },
    password: {
        type: String,
        required: [true, 'Password es obligatorio']
    },
    imagen: {
        type: String
    },
    role: {
        type: String,
        default: 'USER_ROLE',
        required: true
    },
    estado: {
        type: Boolean,
        default: true
    },
    google: {
        type: Boolean,
        default: false
    }
});

/**
 * al sobreescribir algún método, esto debe ser una función normal
 * ya que vamos a usar el objeto this
 * ...usuario unificamos el resto de campos quitando __version y password
 * 
 * -- estos datos se quitan del json a nivel global de la aplicación
 * 
 * Cuando se mande llamar la función toJSON, se va a ejecutar está función
 */
UsuarioSchema.methods.toJSON = function() {

    const { __v, password, _id, ...usuario } = this.toObject();
    usuario.uid = _id;
    return usuario;
};

/**
 * Primero es el nombre del Schema, en este caso Usuarios,
 * segundo es el schema que creamos
 */
module.exports = model('Usuarios', UsuarioSchema);