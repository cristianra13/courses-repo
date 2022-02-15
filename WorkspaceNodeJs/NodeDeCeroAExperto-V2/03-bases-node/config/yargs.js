const { describe } = require('yargs');

const argv = require('yargs')
    .option('b', {
        alias: 'base',
        type: 'number',
        demandOption: true,
        describe: 'Valor para la base de la tabla de multipliar'
    })
    .check((argv, options) => {
        if (isNaN(argv.b)) {
            throw new Error('La base debe ser un numero');
        }
        return true; // si no hay error
    })
    .option('l', {
        alias: 'listar',
        type: 'boolean',
        demandOption: false,
        default: false,
        describe: 'Bandera para mostrar o no la tabla en consola'
    })
    .option('h', {
        alias: 'hasta',
        type: 'number',
        describe: 'Indica hasta donde se va a imprimir la tabla de miltiplicar'
    })
    .argv; // extrae de este paquete el argv

// module.exports = {}; // hacer la exportación por defecto de este archivo
module.exports = argv;