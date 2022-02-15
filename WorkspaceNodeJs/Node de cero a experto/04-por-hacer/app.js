//const { require } = require("yargs");

//const argv = require('yargs').argv;
const argv = require('./config/yargs').argv;
const porHacer = require('./por-hacer/por-hacer')

console.log(argv);

let comando = argv._[0]

switch (comando) {
    case 'crear':
        console.log('Crear TODO');
        /**
         * Recive por argumento la descripción la cual está en argv que son los datos de entrada por consola
         */
        let tarea = porHacer.crear(argv.descripcion)
        console.log(tarea);
        break;
    case 'listar':
        let listado = porHacer.getListado()
        for (let tarea of listado) {
            console.log('========Por hacer========'.green)
            console.log(tarea.descripcion)
            console.log(`Estado: ${tarea.comnpletado}`);
            console.log('========================='.green);
        }

        break;
    case 'actualizar':
        let actualizado = porHacer.actualizar(argv.d, argv.c)

        console.log(actualizado)
        break;
    case 'borrar':
        let borrado = porHacer.borrar(argv.d)
        console.log(borrado);
        break;
    default:
        console.log('Comando no reconocido');
        break;
}