const inquirer = require('inquirer');
require('colors');


const preguntas = [{
    type: 'list',
    name: 'opcion',
    message: '¿ Que desea hacer ?',
    choices: [{
            value: 1,
            name: `${'1.'.green} Buscar ciudad`
        },
        {
            value: 2,
            name: `${'2.'.green} Historial`
        },
        {
            value: 0,
            name: `${'0.'.green} Salir`
        }
    ]
}];


const inquirerMenu = async() => {

    console.clear();
    console.log('=============================='.green);
    console.log('    Seleccione una opción    '.green);
    console.log('==============================\n'.green);

    // opcion es el name que se agrega a preguntas
    const { opcion } = await inquirer.prompt(preguntas);

    return opcion;
}

const pausa = async() => {
    const question = [{
        type: 'input',
        name: 'enter',
        message: `Presion ${'ENTER'.green} para continuar`
    }];

    console.log();
    await inquirer.prompt(question);
}


const leerInput = async(message) => {
    const question = [{
        type: 'input',
        name: 'desc',
        message,
        validate(value) {
            // obligamos a que escriban algo
            if (value.lenght === 0) {
                return 'Por favor ingrese un valor';
            }
            return true;
        }
    }];

    // le mostramos esto al usuario
    const { desc } = await inquirer.prompt(question);
    return desc;
}

const listarCiudades = async(ciudades = []) => {
    console.log();
    const choices = ciudades.map((ciudad, i) => {
        const idx = i + 1;
        return {
            value: ciudad.id,
            name: `${idx}. ${ciudad.nombre}`
        };
    });

    // agregaos al inicio del arreglo
    choices.unshift({
        value: '0',
        name: '0.'.green + ' Cancelar'
    })

    const preguntas = [{
        type: 'list',
        name: 'id',
        message: 'Seleccione ciudad:',
        choices
    }]
    const { id } = await inquirer.prompt(preguntas);
    return id;
}

const confirmar = async(message) => {

    // devuelve un valor booleano ya que el confirm funciona de esa manera
    const question = [{
        type: 'confirm',
        name: 'ok',
        message
    }];

    const { ok } = await inquirer.prompt(question);
    return ok;
}


const mostrarListadoCheckList = async(tareas = []) => {
    console.log();

    const choices = tareas.map((tarea, i) => {
        const idx = `${i + 1}`.green;
        return {
            value: tarea.id,
            name: `${idx}. ${tarea.descripcion}`,
            checked: (tarea.completadoEn) ? true : false
        };
    });

    const pregunta = [{
        type: 'checkbox',
        name: 'ids',
        message: 'Seleccione',
        choices
    }]
    const { ids } = await inquirer.prompt(pregunta);
    return ids;
}

module.exports = {
    inquirerMenu,
    pausa,
    leerInput,
    listarCiudades,
    confirmar,
    mostrarListadoCheckList
}