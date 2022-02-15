const { response } = require('express');
const bcrypt = require('bcryptjs');

// es un estandar colocar la u mayuscula
const Usuario = require('../models/usuario');



const getUsers = async(req, res = response) => {

    /**
     * Con page le enviamos un valor y desde ese valor nos trae los
     * registros depeniendo de el limit,
     * ej: si agregamos en el path, ..?page=10&limit5
     * le estamos indicando que vamos a traer desde el registro numero 10 hasta el 15
     */
    const { page = 1, limit = 5 } = req.query;
    const quesry = { estado: true };

    /**
     * responde un arreglo de promesas,
     * espera hasta que se ejecuten las dos promesas y si alguna da error, el resto tambien
     * 
     * esto se hace porque si una consulta dura mucho tiempo y la otra igual,
     * tardaria en dar la respuesta final.
     * 
     * [] desestructuración de arreglos
     */
    const [total, usuarios] = await Promise.all([
        Usuario.countDocuments(quesry),
        Usuario.find(quesry)
        .skip(Number(page))
        .limit(Number(limit))
    ])

    res.json({
        total,
        usuarios
    });
};

const postUsers = async(req, res = response) => {

    // tomamos los campos que nos interesan
    const { nombre, password, email, role } = req.body;
    const usuario = new Usuario({ nombre, password, email, role });

    // encriptar la contraseña
    // primero asignamos el número de vueltas para la contraseña
    const salt = bcrypt.genSaltSync();
    // hacemos el hash de la contraseña
    usuario.password = bcrypt.hashSync(password, salt);

    // guardamos en BD
    await usuario.save();

    res.json(usuario);
};

const putUsers = async(req, res = response) => {

    const { id } = req.params;
    console.log('Id:', id);
    const { _id, password, google, ...usuario } = req.body;

    if (password) {
        const salt = bcrypt.genSaltSync();
        usuario.password = bcrypt.hashSync(password, salt);
    }
    const usuarioDb = await Usuario.findByIdAndUpdate(id, usuario);


    res.json({
        ok: true,
        usuario: usuarioDb
    });
};

const deleteUsers = async(req, res = response) => {

    // debe ir entre llaves id porque lo castea de un objeto a un objeto de mongo 
    const { id } = req.params;

    const usuarioEliminado = await Usuario.findByIdAndUpdate(id, { estado: false });

    const usuarioAutenticado = req.usuario;

    res.json({
        usuarioEliminado,
        usuarioAutenticado
    });
};


module.exports = {
    getUsers,
    postUsers,
    putUsers,
    deleteUsers
}