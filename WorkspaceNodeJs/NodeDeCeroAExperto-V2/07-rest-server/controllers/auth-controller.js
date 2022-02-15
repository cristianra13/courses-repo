 const { response } = require("express");
 const bcrypyjs = require('bcryptjs');

 const Usuario = require('../models/usuario');
 const generarJWT = require("../helpers/generar-jwt");
 const { googleVerify } = require('../helpers/google-verify');

 const login = async(req, res = response) => {

     const { email, password } = req.body;

     try {

         // verificar si email existe
         const usuario = await Usuario.findOne({ email });
         if (!usuario) {
             return res.status(400).json({
                 msg: 'Usuario o contraseña invalidos'
             });
         }

         // validar si usuario está activo en BD
         if (!usuario.estado) {
             return res.status(400).json({
                 msg: 'Usuario inactivo'
             });
         }

         // verificar la contraseña
         const validPassword = bcrypyjs.compareSync(password, usuario.password);
         if (!validPassword) {
             return res.status(400).json({
                 msg: 'Usuario o contraseña invalidos'
             });
         }

         // generar JWT
         const token = await generarJWT(usuario.id);

         res.json({
             usuario,
             token
         });

     } catch (error) {
         console.log(error);
         res.status(500).json({
             msg: 'Ocurrio un error\nComuniquese con el administrador'
         });
     }

 };

 const googleSignIn = async(req, res = response) => {

     try {
         const { id_token } = req.body;
         const { email, nombre, imagen } = await googleVerify(id_token);

         // validar si el email existe en la BD propia
         let usuario = await Usuario.findOne({ email });

         if (!usuario) {
             // creamos el usuario
             const data = {
                 nombre,
                 email,
                 password: '-',
                 imagen,
                 google: true
             };

             usuario = new Usuario(data);
             await usuario.save();
         }

         /**
          * si el usuario autenticado por google tiene estado en false
          */
         if (!usuario.estado) {
             return res.status(401).json({
                 msg: 'Comuniquese con el administrador, usuario bloqueado'
             });
         }

         const token = await generarJWT(usuario.id);

         res.json({
             usuario,
             token
         });

     } catch (error) {
         console.log(error);
         res.status(400).json({
             msg: 'Token de google no es valido'
         });
     }
 };

 module.exports = {
     login,
     googleSignIn
 }