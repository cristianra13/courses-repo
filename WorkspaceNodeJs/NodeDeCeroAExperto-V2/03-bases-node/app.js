const { crearArchivotabla, crearArchivoTab } = require('./helpers/multiplicar')
require('colors'); // require sencillo
const { number, option } = require('yargs');
const argv = require('./config/yargs');


console.clear();

crearArchivoTab(argv.b, argv.l, argv.h)
    .then(nombre => console.log(nombre.rainbow, 'creado'.rainbow))
    .catch(err => console.log(err))