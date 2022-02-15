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


// definimos la primera promesa
let getEmpleado = (id) => {

    /**
     * La promesa tiene dos callbacks resolve y reject
     * El resolve se ejecuta si la promesa es exitosa, es decir, si logra encontrar un empleado
     * y el reject en caso contrario
     * Los nombres de resolve y reject son opcionales, es buena practica dejarlos de está forma
     */
    return new Promise((resolve, reject) => {
        /**
         * Se retorna un empleado el cual se busca en el arreglo de empleados por id
         * si cumple alguno, ete es retornado
         */
        let empleadoDB = empleados.find(empleado => empleado.id === id);

        if (!empleadoDB) {
            reject(`No existe empleado con id ${id}`)
        } else {
            // mandamos null inicialmente porque sabemos que no hay ningún error
            resolve(empleadoDB)
        }
    });
}


let getSalario = (empleado) => {
    return new Promise((resolve, reject) => {
        let SalarioDB = salarios.find(salario => salario.id === empleado.id);

        if (!SalarioDB) {
            reject(`No existe algún salario para el empleado ${empleado.nombre}`)
        } else {
            resolve({ id: empleado.id, nombre: empleado.nombre, salario: SalarioDB.salario })
        }
    });
}

/**
 * con la ejecuciín de .then() se ejecuta la promesa
 * Dentro, agregamos el resolve el cual es el resolve
 */
/* getEmpleado(1).then(empleado => {
    getSalario(empleado).then(resp => {
        console.log(`El salario de ${resp.nombre} es de $${resp.salario}`)
    }, (err) => {
        console.log(err);
    })
}, (err) => {
    // tomamos el error y lo mostramos
    console.log(err);
}); */



/**
 * Promesas encadenadas
 */
getEmpleado(1).then(empleado => {
        /**
         * Cuando se vea un return como este caso, sabemos que el siguiente .then()
         * es el que corresponde a este return
         */
        return getSalario(empleado)
    })
    .then(resp => {
        console.log(`El salario de ${resp.nombre} es de ${resp.salario}`);
    })
    .catch(err => {
        console.log(err);
    });