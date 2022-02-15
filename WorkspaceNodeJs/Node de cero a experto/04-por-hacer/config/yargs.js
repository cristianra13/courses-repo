const opciones = {
    descripcion: {
        demand: true,
        alias: 'd'
    },
    completado: {
        demand: false,
        alias: 'c',
        default: true
    }
}

const argv = require('yargs')
    .command('crear', 'Crea registro por hacer', opciones)
    .command('actualizar', 'Actualiza registros por hacer', opciones)
    .command('listar', 'Obtener todas las tareas por hacer')
    .command('borrar', 'Borrar una tarea', opciones)
    .help()
    .argv

module.exports = {
    argv
}