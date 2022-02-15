const { response } = require("express");
const { ObjectId, isValidObjectId } = require("mongoose");
const { Usuario, Categoria, Producto } = require("../models");

const coleccionesPermitidas = [
    'categoria',
    'productos',
    'roles',
    'usuarios'
];

const buscarUsuarios = async(termino = '', res = response) => {
    const esMongoId = isValidObjectId(termino);
    if (esMongoId) {
        const usuario = await Usuario.findById(termino);
        return res.json({
            results: (usuario) ? [usuario] : []
        });
    }
    // busquedas insensibles a mayusculas y minusculas
    const regex = new RegExp(termino, 'i');

    const query = {
        // esto nos dice que busca por nombre o por correo según lo que encuentre en mongo
        $or: [{ nombre: regex }, { email: regex }],
        $and: [{ estado: true }] // y debe cumplir está condición
    };

    const [total, usuarios] = await Promise.all([
        Usuario.countDocuments(query),
        Usuario.find(query)
    ]);


    res.json({
        total,
        results: usuarios
    });
}

const buscarCategorias = async(termino = '', res) => {
    const esMongoId = isValidObjectId(termino);
    if (esMongoId) {
        const categoria = await Categoria.findById(termino);
        return res.json({
            results: (categoria) ? [categoria] : []
        });
    }
    // busquedas insensibles a mayusculas y minusculas
    const regex = new RegExp(termino, 'i');
    const query = { nombre: regex, estado: true };

    const [total, categorias] = await Promise.all([
        Categoria.countDocuments(query),
        Categoria.find(query)
    ]);


    res.json({
        total,
        results: categorias
    });

}

const buscarProductos = async(termino = '', res) => {
    const esMongoId = isValidObjectId(termino);
    if (esMongoId) {
        const producto = await Producto.findById(termino)
            .populate('categorias', 'nombre');
        return res.json({
            results: (producto) ? [producto] : []
        });
    }

    // busquedas insensibles a mayusculas y minusculas
    const regex = new RegExp(termino, 'i');
    const query = { nombre: regex, estado: true };

    const [total, productos] = await Promise.all([
        Producto.countDocuments(query),
        Producto.find(query)
        .populate('categorias', 'nombre')
    ]);


    res.json({
        total,
        results: productos
    });

}

const buscar = (req, res = response) => {

    const { coleccion, termino } = req.params;

    if (!coleccionesPermitidas.includes(coleccion)) {
        res.status(400).json({
            msg: `Las colecciones permitidas son: ${coleccionesPermitidas}`
        });
    }

    switch (coleccion) {
        case 'productos':
            buscarProductos(termino, res);
            break;
        case 'categorias':
            buscarCategorias(termino, res);
            break;
        case 'usuarios':
            buscarUsuarios(termino, res);
            break;
        default:
            res.status(500).json({
                msg: 'Se me olvido hacer esta busqueda'
            });
            break;
    }
}


module.exports = {
    buscar
}