const fs = require('fs');

let listarTabla = (base, limite = 10) => {

    for (let i = 1; i <= limite; i++) {
        console.log(`${base} * ${i} = ${base * i}`);
    }
};


// module.exports.crearArchivo() = (base)  de está forma también se puede exportar
crearArchivo = (base, limite) => {
    return new Promise((resolve, reject) => {

        if (!Number(base)) {
            reject(`la base ingresada no es un número: ${base}`);
            return;
        }

        if (!Number(limite)) {
            reject(`el limite ingresada no es un número: ${limite}`);
            return;
        }

        let data = '';

        for (let i = 1; i <= limite; i++) {
            console.log(`${base} * ${i} = ${base * i}`);
            data += `${base} * ${i} = ${base * i} \n`;
        }

        fs.writeFile(`tablas/tabla-${base}.txt`, data, (err) => {
            if (err)
                reject(err);
            else
                resolve(`tabla-${base}.txt`);
            console.log('The file has been saved!');
        });
    });
};

/**
 * añadimos los objetos que queremos utilizar de forma global
 * 
 * está es una forma de hacerlo
 */
module.exports = {
    crearArchivo, // el valor de este sería crearArchivo, pero en ES6 no es necesario ya que tienen el mismo nombre 
    listarTabla
}