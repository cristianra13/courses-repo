const opciones = {
    base: { // en este caso, por consola se debe ingresar --base
        demand: true, // quiere decir que es un parametro obligatorio
        alias: 'b' // alias con el que s epuede llamar desde consola
    },
    limite: { // segundo parametro
        // demand: true,
        alias: 'l',
        default: 10 // valor por defecto en caso de no recibir el limite
    }
}

const argv = require('yargs')
    .command('listar', 'Imprime en consola la tabla de multiplicar', opciones)
    .command('crear', 'Crea archivo de la tabla de multiplicar', opciones)
    .help()
    .argv // nombre con el que se van a obtener los atributos de los yargs

module.exports = {
    argv
}