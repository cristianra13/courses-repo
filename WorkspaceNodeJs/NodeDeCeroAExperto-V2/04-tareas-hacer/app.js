// primero las importaciones de terceros luego las nuestras
require('colors');

const { guardarDB, leerDB } = require('./helpers/guardar-archivo');
const {
    inquirerMenu,
    pausa,
    leerInput,
    listadoTareasBorrar,
    confirmar,
    mostrarListadoCheckList
} = require('./helpers/inquirer');
const { pausaVentana } = require('./helpers/mensajes');
const Tarea = require('./helpers/models/tarea');
const Tareas = require('./helpers/models/tareas');


console.clear();

const main = async() => {
    console.log('Hola mundo');

    let opt = '';
    const tareas = new Tareas();
    const tareasDB = leerDB();

    if (tareasDB) {
        // cargar tareas
        tareas.cargarTareasFromArray(tareasDB);
    }

    do {
        // le decimos que espere hasta que haya una resolución de mostrar menú
        opt = await inquirerMenu();

        switch (opt) {
            case '1':
                // crear opción
                const desc = await leerInput('Descripción: ');
                tareas.creatTarea(desc);
                break;
            case '2':
                // listamos las tareas
                tareas.listadoCompleto();
                break;
            case '3':
                // listamos las tareas completadas
                tareas.listarPendientesCompletadas(true);
                break;
            case '4':
                // listamos las tareas pendientes
                tareas.listarPendientesCompletadas(false);
                break;
            case '5': // completado o pendiente
                const ids = await mostrarListadoCheckList(tareas.listadoArr);
                tareas.toggleCompletdas(ids);
                break;
            case '6':
                // agregamos el awair para que termine de construir el menú
                const id = await listadoTareasBorrar(tareas.listadoArr);
                if (id !== '0') {

                    const confirmarBorrar = await confirmar('Estás seguro de borrar el registro ?');

                    if (confirmarBorrar) {
                        tareas.borrarTareas(id);
                        console.log('Tarea eliminada!');
                    }
                }
                break;
        }

        guardarDB(tareas.listadoArr);

        // if (opt !== '0') await pausaVentana();
        await pausa();
    }
    while (opt !== '0');

}


main();