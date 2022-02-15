require('colors');

console.clear();

const mostrarMEnu = () => {

    return new Promise((resolve) => {
        console.log('=============================='.green);
        console.log('    Seleccione una opción    '.green);
        console.log('==============================\n'.green);

        console.log(`${'1.'.green} Crear tarea`);
        console.log(`${'2.'.green} Listar tareas`);
        console.log(`${'3.'.green} Listar tareas completada`);
        console.log(`${'4.'.green} Listar tareas pendientes`);
        console.log(`${'5.'.green} Completar tarea(s)`);
        console.log(`${'6.'.green} Borrar tarea`);
        console.log(`${'0.'.green} Salir \n`);


        const readLine = require('readline').createInterface({
            // con esto esperamos el ingreso por consola y el enter del usuario
            input: process.stdin,
            output: process.stdout
        });

        readLine.question('Seleccione una opción: ', (opt) => {
            readLine.close();
            // el valor que escribe la persona, lo mandamos en el opt
            resolve(opt);
        });
    });


}

const pausaVentana = () => {

    return new Promise((resolve) => {

        const readLine = require('readline').createInterface({
            // con esto esperamos el ingreso por consola y el enter del usuario
            input: process.stdin,
            output: process.stdout
        });

        readLine.question(`\nPresiones ${'ENTER'.green} para continuar\n`, (opt) => {
            readLine.close();
            resolve();
        });

    });
}

module.exports = {
    mostrarMEnu,
    pausaVentana
}