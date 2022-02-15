const { response } = require("express");
const { Producto } = require('../models');


const obtenerProductos = async(req, res = response) => {

    const { page = 0, limit = 5 } = req.query;
    const query = { estado: true };
    const [total, productos] = await Promise.all([
        Producto.countDocuments(query),
        Producto.find(query)
        .populate('usuarios', 'nombre')
        .populate('categorias', 'nombre')
        .skip(Number(page))
        .limit(Number(limit))
    ]);

    res.json({
        total,
        productos
    });
}

const obtenerProducto = async(req, res = response) => {
    const { id } = req.params;
    const producto = await Producto.findById(id)
        .populate('usuarios', 'nombre email')
        .populate('categorias', 'nombre');

    res.json(producto);
}

const crearProducto = async(req, res = response) => {

    const { estado, usuario, ...body } = req.body;
    const productoDB = await Producto.findOne({ nombre: body.nombre.toUpperCase() });
    if (productoDB) {
        return res.status(400).json({
            msg: `El producto '${productoDB.nombre}', ya existe`
        });
    }

    // generar data a guardar
    const data = {
        ...body,
        nombre: body.nombre.toUpperCase(),
        usuario: req.usuario._id
    }

    const producto = await new Producto(data);

    // guardar en DB
    await producto.save();

    res.status(201).json(producto);
}

const actualizarProducto = async(req, res = response) => {

    const { id } = req.params;
    const { estado, usuario, ...data } = req.body;
    if (data.nombre)
        data.nombre = nombre.toUpperCase();
    data.usuario = req.usuario._id;
    const producto = await Producto.findByIdAndUpdate(id, data, { new: true });

    res.json({
        producto
    });
}

const eliminarProducto = async(req, res = response) => {
    const { id } = req.params;
    const producto = await Producto.findByIdAndUpdate(id, { estado: false }, { new: true });

    res.json(producto);
}

module.exports = {
    obtenerProductos,
    obtenerProducto,
    crearProducto,
    actualizarProducto,
    eliminarProducto
}