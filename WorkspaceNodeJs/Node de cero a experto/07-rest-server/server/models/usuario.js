const mongoose = require('mongoose');
const uniqueValidator = require('mongoose-unique-validator');

/**
 * Definimos los roles validos
 */
let rolesValidos = {
    values: ['ADMIN_ROLE', 'USER_ROLE'],
    message: '{VALUE} no es un rol válido'
}

/**
 * Creamos el schema
 */
let Schema = mongoose.Schema;


/**
 * Definimos el Schema
 */
let usuarioSchema = new Schema({
    // Campos de la colleción

    nombre: {
        // restricciones del campo
        type: String,
        required: [true, 'El campo nombre es requerido'] // en comilla el mensaje en ncaso de no cumplir la regla
    },
    password: {
        type: String,
        required: [true, 'El campo password es requerido']
    },
    email: {
        type: String,
        unique: true,
        required: [true, 'El campo email es requerido']

    },
    img: {
        type: String
    },
    role: {
        type: String,
        default: 'USER_ROLE',
        enum: rolesValidos
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
 * El metodo toJSON siempre se llama cuando se intenta imprimir, en este caso cuando lo retornamos
 */
usuarioSchema.methods.toJSON = function() {
    // elñ this es lo que sea que haya en ese momento en el objeto
    let user = this;
    let userObj = user.toObject();


    // Eliminamos el campo password del objeto para que este no sea retornado
    delete userObj.password;

    return userObj;
}

// nos sirve para campos marcados con unique, podemos mandar un error en tipo json ordenado
usuarioSchema.plugin(uniqueValidator, { message: '{PATH} debe de ser único' });

// este es el nombre que se le va a dar al Schema, y este va a tener toda la configuración de usuarioSchema
module.exports = mongoose.model('Usuario', usuarioSchema);