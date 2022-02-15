const express = require('express');
const fs = require('fs');
const path = require('path'); // para construir el path absoluto
const { verificaTokenImg } = require('../middlewares/autenticacion');

let app = express();


app.get('/imagen/:tipo/:img', verificaTokenImg, (req, resp) => {

    let tipo = req.params.tipo;
    let img = req.params.img;

    /**
     * Está función lee el content-type del archivo y regresa el el content-type de este
     * 
     * Especificiamos el path absoluto con __dirname
     */

    // verificamos que la ruta de la imagen exista
    let pathImagen = path.resolve(__dirname, `../../uploads/${tipo}/${ img }`);

    if (fs.existsSync(pathImagen)) {
        resp.sendFile(pathImagen)
    } else {
        let noImagePath = path.resolve(__dirname, '../assets/no-image.jpg');
        resp.sendFile(noImagePath);
    }

});


module.exports = app;