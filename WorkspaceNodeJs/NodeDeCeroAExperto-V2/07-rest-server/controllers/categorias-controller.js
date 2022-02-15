const { response } = require('express');
const { Categoria } = require('../models');

// obtenercategorias - paginado - opcional - totalCategorias - populate
const obtenerCategorias = async(req, res = response) => {

    const { page = 0, limit = 5 } = req.query;
    const query = { estado: true };

    const [total, categorias] = await Promise.all([
        Categoria.countDocuments(query),
        Categoria.find(query)
        .populate('usuarios', 'nombre')
        .skip(Number(page))
        .limit(Number(limit))
    ]);

    res.json({
        total,
        categorias
    });
}

// obtenercategoria  - populate - {}

const obtenerCategoria = async(req, res = response) => {

    const { id } = req.params;
    const categoria = await Categoria.findById(id)
        .populate('usuarios', 'nombre');

    res.json({
        categoria
    })
}

const crearCategoria = async(req, res = response) => {

    const nombre = req.body.nombre.toUpperCase();
    const categoriaDB = await Categoria.findOne({ nombre });

    if (categoriaDB) {
        return res.status(400).json({
            msg: `La categoria '${categoriaDB.nombre}', ya existe`
        });
    }

    // generar data a guardar
    const data = {
        nombre,
        usuario: req.usuario._id
    }

    const categoria = await new Categoria(data);

    // guardar en DB
    await categoria.save();

    res.status(201).json({
        categoria
    });

}

const actualizarCategoria = async(req, res = response) => {
    const { id } = req.params;
    const { nombre, usuario, ...data } = req.body;

    data.nombre = nombre.toUpperCase();
    data.usuario = req.usuario._id;

    const categoriaDB = await Categoria.findByIdAndUpdate(id, data, { new: true });

    res.json({
        categoria: categoriaDB
    });
}

const eliminarCategoria = async(req, res = response) => {
    const { id } = req.params;
    const categoriaDB = await Categoria.findByIdAndUpdate(id, { estado: false });

    res.json({
        categoria: categoriaDB
    });
}

module.exports = {
    crearCategoria,
    obtenerCategorias,
    obtenerCategoria,
    actualizarCategoria,
    eliminarCategoria
}