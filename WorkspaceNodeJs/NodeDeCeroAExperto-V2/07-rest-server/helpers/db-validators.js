const { Categoria, Role, Usuario, Producto } = require('../models');

const esRolValido = async(rol = '') => {

    const existeRol = await Role.findOne({ role: rol }); // buscamos el nombre del rol que estamos validando
    if (!existeRol) {
        throw new Error(`El rol ${rol} no es valido!`);
    }

}

const esEmailValido = async(email = '') => {

    // verificar si el correo existe
    const existeEmail = await Usuario.findOne({ email });

    if (existeEmail) {
        throw new Error('Email ya existe!');
    }
}

const existeUsuarioById = async(id) => {

    // verificar si el correo existe
    const existeUsuario = await Usuario.findById(id);

    if (!existeUsuario) {
        throw new Error(`El id ${id} no existe!`);
    }
}

const existeCategoriaById = async(id) => {

    // verificar si el correo existe
    const existeCategoria = await Categoria.findById(id);

    if (!existeCategoria) {
        throw new Error(`La categoria '${id}' no existe`);
    }
}

const existeProductoById = async(id) => {

    // verificar si el correo existe
    const existeProducto = await Producto.findById(id);

    if (!existeProducto) {
        throw new Error(`El producto con '${id}' no existe`);
    }
}

/**
 * Validar colecciones permitidas
 */
const coleccionesPermitidas = (coleccion = '', colecciones = []) => {

    const incluida = colecciones.includes(coleccion);
    if (!incluida) {
        throw new Error(`La colección --|${coleccion}|-- no es permitida, ${colecciones}`);
    }
    return true;
}

module.exports = {
    esRolValido,
    esEmailValido,
    existeUsuarioById,
    existeCategoriaById,
    existeProductoById,
    coleccionesPermitidas
}