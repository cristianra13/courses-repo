const mongoose = require('mongoose');
const uniqueValidator = require('mongoose-unique-validator');


/**
 * Creamos el schema
 */
let Schema = mongoose.Schema;


let categoriaSchema = new Schema({

    descripcion: {
        // restricciones del campo
        type: String,
        unique: true,
        required: [true, 'El campo nombre es requerido'] // en comilla el mensaje en ncaso de no cumplir la regla
    },
    usuario: {
        type: Schema.Types.ObjectId,
        ref: 'Usuario' // referenciamos el schema Usuario
    }

});

// nos sirve para campos marcados con unique, podemos mandar un error en tipo json ordenado
categoriaSchema.plugin(uniqueValidator, { message: '{PATH} debe de ser único' });

// este es el nombre que se le va a dar al Schema, y este va a tener toda la configuración de usuarioSchema
module.exports = mongoose.model('Categoria', categoriaSchema);