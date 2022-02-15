/**
 * Al inicio se acostumbra agregar los require's
 * 
 * Existen tres tipos de requires: 
 * require('fs'); // el cuaol es el por defecto de node que viene instalado
 * 
 * require('express'); // este es un paquete que se instala ya que no viene nativo de node
 * 
 * require(''); // por ultimo, los requres de archivos que nosotros hacemos dentro del proyecto,
 * Estos se identifican con un ./path-archivo
 */
//const fs = require('express');
//const fs = require('./miArchivo');

const argv = require('./config/yargs').argv;

/* const argv = require('yargs')
    .command('listar', 'Imprime en consola la tabla de multiplicar', {
        // recibe como tercer parametro, la configuración de parametros o los flags que se puede recibir
        base: { // en este caso, por consola se debe ingresar --base
            demand: true, // quiere decir que es un parametro obligatorio
            alias: 'b' // alias con el que s epuede llamar desde consola
        },
        limite: { // segundo parametro
            // demand: true,
            alias: 'l',
            default: 10 // valor por defecto en caso de no recibir el limite
        }
    })
    .command('crear', 'Crea archivo de la tabla de multiplicar', {
        // recibe como tercer parametro, la configuración de parametros o los flags que se puede recibir
        base: { // en este caso, por consola se debe ingresar --base
            demand: true, // quiere decir que es un parametro obligatorio
            alias: 'b' // alias con el que s epuede llamar desde consola
        },
        limite: { // segundo parametro
            // demand: true,
            alias: 'l',
            default: 10 // valor por defecto en caso de no recibir el limite
        }
    })
    .help()
    .argv // nombre con el que se van a obtener los atributos de los yargs */

// const fs = require('fs');

const multiplicar = require('./multiplicar/multiplicar') // se puede agregar la extensión del archivo, per es opcional

// con destructuración
const { crearArchivo, listarTabla } = require('./multiplicar/multiplicar')

// let base = 'q';

// console.log(multiplicar.crearArchivo(4));

/**
 * otro objeto que se exporta importante es  process
 * Cuando node se ejecuta, ya crea esa variable de entorno por lo que no se debe definir
 * 
 * Con argv podemos recibir parametros enviados desde consola.
 * 
 * en este caso vamos a recibir el valor el cual est+a en la tercera posición del arreglo que llega
 */
// console.log(process.argv);

let argv2 = process.argv;
/* let parametro = argv[2]
let base = parametro.split('=')[1] */


let comando = argv._[0] // con guión bajo, hacemos referencia al arreglo

switch (comando) {
    case 'listar':
        listarTabla(argv.b, argv.l)
        break;
    case 'crear':
        crearArchivo(argv.b, argv.l)
            .then((archivo) => console.log(`Archivo creado: ${archivo}`))
            .catch((err) => console.log(err))
        break;
    default:
        console.log('Comando no reconocido');
}


console.log(argv)