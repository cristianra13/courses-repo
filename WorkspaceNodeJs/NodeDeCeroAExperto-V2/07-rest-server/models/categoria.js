const { Schema, model } = require('mongoose');

const categoriaSchema = new Schema({
    nombre: {
        type: String,
        require: [true, 'Elnombre de la categoria es requerido'],
        unique: true
    },
    estado: {
        type: Boolean,
        default: true,
        require: true
    },
    usuario: {
        type: Schema.Types.ObjectId,
        ref: 'usuario', // referencia al Schema de usuario
        require: true
    }
});

categoriaSchema.methods.toJSON = function() {
    const { __v, estado, ...data } = this.toObject();
    return data;
}

module.exports = model('Categoria', categoriaSchema);