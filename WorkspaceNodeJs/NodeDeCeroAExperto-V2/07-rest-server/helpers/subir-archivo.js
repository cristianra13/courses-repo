const path = require('path');
const { v4: uuidv4 } = require('uuid');
const { response } = require("express");


const extensiones = ['png', 'jpg', 'jpeg', 'gif'];
const subirArchivo = (files, extensionesValidas = extensiones, carpeta = '') => {

    return new Promise((resolve, reject) => {

        const { archivo } = files;
        const nombreCortado = archivo.name.split('.');
        const extension = nombreCortado[nombreCortado.length - 1];

        // extensiones permitidas
        if (!extensionesValidas.includes(extension)) {
            return reject(`la extensión ${extension} no es permitida, son validas ${extensionesValidas}`);
        }
        const nombreTempArchivo = uuidv4() + '.' + extension;
        // donde vamos a colocar el archivo
        const uploadPath = path.join(__dirname, '../uploads/', carpeta, nombreTempArchivo);

        // Use the mv() method to place the file somewhere on your server
        archivo.mv(uploadPath, (err) => {
            if (err) {
                console.log(err);
                reject(err);
            }

            resolve(nombreTempArchivo);
        });

    });

}

module.exports = {
    subirArchivo
}