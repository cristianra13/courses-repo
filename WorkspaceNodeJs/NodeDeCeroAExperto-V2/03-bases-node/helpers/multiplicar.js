const { rejects } = require('assert');
const fs = require('fs');


const crearArchivotabla = (numero = 0) => {

    return new Promise((resolve, reject) => {
        let salida = '';
        for (let i = 1; i <= 10; i++) {
            salida += `${numero} X ${i} = ${numero * i}\n`;
        }
        fs.writeFileSync(`tabla-${numero}.txt`, salida);
        resolve(`tabla-${numero}.txt`);
    });


}

const crearArchivoTab = async(base = 0, listar, hasta = 10) => {

    let salida = '';
    try {
        for (let i = 1; i <= hasta; i++) {
            salida += `${base} X ${i} = ${base * i}\n`;
        }

        listar ? console.log(salida) : '';

        fs.writeFileSync(`./salida/tabla-${base}.txt`, salida);

        return `tabla-${base}.txt`;
    } catch (error) {
        throw error;
    }

}

module.exports = {
    crearArchivotabla,
    crearArchivoTab
};