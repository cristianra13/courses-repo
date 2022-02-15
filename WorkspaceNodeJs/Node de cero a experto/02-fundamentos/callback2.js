// simulación de base de datos

let empleados = [{
        id: 1,
        nombre: 'Cristian'
    },
    {
        id: 2,
        nombre: 'Juana'
    },
    {
        id: 3,
        nombre: 'Maria'
    }
];

let salarios = [{
        id: 1,
        salario: 1000
    },
    {
        id: 2,
        salario: 1800
    }
];

// función para obtener empleado por id
let getEmpleado = (id, callback) => {
    /**
     * Se retorna un empleado el cual se busca en el arreglo de empleados por id
     * si cumple alguno, ete es retornado
     */
    let empleadoDB = empleados.find(empleado => empleado.id === id);

    if (!empleadoDB) {
        callback(`No existe empleado con id ${id}`)
    } else {
        // mandamos null inicialmente porque sabemos que no hay ningún error
        callback(null, empleadoDB)
    }
}

let getSalario = (empleado, callback) => {

    let salarioEmpleado = salarios.find(salario => salario.id === empleado.id);

    if (!salarioEmpleado) {
        callback(`No existe salario para el empleado: ${empleado.nombre}`)
    } else {
        /**
         * Cuando se requeere retornar una propiedad que tenga mas de un valor
         * se retorna con {}, en este caso un objeto
         */
        callback(null, { id: empleado.id, nombre: empleado.nombre, salario: salarioEmpleado.salario })
    }
}

// llamamos la función
/**
 * Como primer parametro del callback enviamos el error y 
 * como segundo parametro, el empleado que recibimos de la función getEmpleado
 */
getEmpleado(3, (err, empleado) => {
    if (err) {
        // manejo del error
        return console.log(err);
    }

    getSalario(empleado, (err, response) => {
        if (err) {
            // manejo del error
            return console.log(err);
        }
        console.log(`el salario de ${response.nombre} es ${response.salario}`);
    });
});