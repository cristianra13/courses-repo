const path = require('path');
const fs = require('fs');

const cloudinary = require('cloudinary').v2;

const { response } = require("express");
const { subirArchivo } = require("../helpers/subir-archivo");
const { Usuario, Producto } = require('../models');
const { model } = require('mongoose');

// configuracion cloudinary
cloudinary.config(process.env.CLOUDINARY_URL);

const cargarArchivos = async(req, res = response) => {

    if (!req.files || Object.keys(req.files).length === 0 || !req.files.archivo) {
        return res.status(400).json({
            msg: 'No hay archivos para subir'
        });
    }

    try {
        // const nombre = await subirArchivo(req.files, undefined, 'imagenes');
        const nombre = await subirArchivo(req.files, undefined, 'imagenes');
        res.json({ nombre });
    } catch (msg) {
        res.status(400).json({ msg });
    }

}

/**
 * Método para cargar imagenes para usuarios y productos
 * @param {*} req 
 * @param {*} res 
 * @returns 
 */
const actualizarImagen = async(req, res = response) => {
    const { id, coleccion } = req.params;
    let modelo;

    switch (coleccion) {
        case 'usuarios':
            modelo = await Usuario.findById(id);
            if (!modelo) {
                return res.status(400).json({
                    msg: `No existe un usuario con el id: ${id}`
                });
            }
            break;

        case 'productos':
            modelo = await Producto.findById(id);
            if (!modelo) {
                return res.status(400).json({
                    msg: `No existe un producto con el id: ${id}`
                });
            }
            break;

        default:
            return res.status(500).json({ msg: 'Olvide validar esto' });
    }

    // limpiar imagenes previas
    if (modelo.img) {
        // Borrar la imagen del servidor
        const pathImagen = path.join(__dirname, '../uploads', coleccion, modelo.img);
        if (fs.existsSync(pathImagen)) {
            // borramos la imagen
            fs.unlinkSync(pathImagen);
        }
    }

    modelo.img = await subirArchivo(req.files, undefined, coleccion);
    await modelo.save();

    res.json({
        modelo
    });
}


const actualizarImagenCloudinary = async(req, res = response) => {
    const { id, coleccion } = req.params;
    let modelo;

    switch (coleccion) {
        case 'usuarios':
            modelo = await Usuario.findById(id);
            if (!modelo) {
                return res.status(400).json({
                    msg: `No existe un usuario con el id: ${id}`
                });
            }
            break;

        case 'productos':
            modelo = await Producto.findById(id);
            if (!modelo) {
                return res.status(400).json({
                    msg: `No existe un producto con el id: ${id}`
                });
            }
            break;

        default:
            return res.status(500).json({ msg: 'Olvide validar esto' });
    }

    // limpiar imagenes previas
    if (modelo.img) {
        // Borrar la imagen de Cloudinary
        const nombreArr = modelo.img.split('/');
        const nombreImg = nombreArr[nombreArr.length - 1];
        const [public_id] = nombreImg.split('.');
        console.log('delete of:', public_id);
        cloudinary.uploader.destroy(public_id);
    }

    const { tempFilePath } = req.files.archivo;
    const { secure_url } = await cloudinary.uploader.upload(tempFilePath, { folder: "RestServer-NodeJs" }).catch(error => console.log(error));

    modelo.img = secure_url;
    await modelo.save();
    res.json(modelo);
}


/**
 * Método para mostrar imagen
 * 
 * @param {*} req 
 * @param {*} res 
 */
const mostrarImagen = async(req, res = response) => {
    const { id, coleccion } = req.params;
    let modelo;

    switch (coleccion) {
        case 'usuarios':
            modelo = await Usuario.findById(id);
            if (!modelo) {
                return res.status(400).json({
                    msg: `No existe un usuario con el id: ${id}`
                });
            }
            break;

        case 'productos':
            modelo = await Producto.findById(id);
            if (!modelo) {
                return res.status(400).json({
                    msg: `No existe un producto con el id: ${id}`
                });
            }
            break;

        default:
            return res.status(500).json({ msg: 'Olvide validar esto' });
    }

    // limpiar imagenes previas
    if (modelo.img) {
        // Borrar la imagen del servidor
        const pathImagen = path.join(__dirname, '../uploads', coleccion, modelo.img);
        if (fs.existsSync(pathImagen)) {
            return res.sendFile(pathImagen);
        }
    }
    // retornamos imagen por defecto
    const pathDefaultImagen = path.join(__dirname, '../assets/default-img/no-image.jpg');
    res.sendFile(pathDefaultImagen);
}


module.exports = {
    cargarArchivos,
    actualizarImagen,
    actualizarImagenCloudinary,
    mostrarImagen
}