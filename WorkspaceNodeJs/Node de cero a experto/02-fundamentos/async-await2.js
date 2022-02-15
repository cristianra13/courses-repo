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
let getEmpleado = async(id) => {

    /**
     * La promesa tiene dos callbacks resolve y reject
     * El resolve se ejecuta si la promesa es exitosa, es decir, si logra encontrar un empleado
     * y el reject en caso contrario
     * Los nombres de resolve y reject son opcionales, es buena practica dejarlos de está forma
     */
    /* return new Promise((resolve, reject) => { */
    /**
     * Se retorna un empleado el cual se busca en el arreglo de empleados por id
     * si cumple alguno, ete es retornado
     */
    let empleadoDB = empleados.find(empleado => empleado.id === id);

    if (!empleadoDB) {
        // reject(`No existe empleado con id ${id}`)
        throw new Error(`No existe empleado con id ${id}`)
    } else {
        // mandamos null inicialmente porque sabemos que no hay ningún error
        //resolve(empleadoDB)
        return empleadoDB
    }
    /* }); */
}


let getSalario = async(empleado) => {
    /* return new Promise((resolve, reject) => { */
    let SalarioDB = salarios.find(salario => salario.id === empleado.id);

    if (!SalarioDB) {
        /* reject(`No existe algún salario para el empleado ${empleado.nombre}`) */
        throw new Error(`No existe algún salario para el empleado ${empleado.nombre}`)
    } else {
        /* resolve({ id: empleado.id, nombre: empleado.nombre, salario: SalarioDB.salario }) */
        return { id: empleado.id, nombre: empleado.nombre, salario: SalarioDB.salario }
    }
    /* }); */
}


let getInformacion = async(id) => {
    let empleado = await getEmpleado(id)
    let res = await getSalario(empleado)
    return `${res.nombre} tiene un salario de $${res.salario}`
}

getInformacion(10)
    .then(mensaje => console.log(mensaje))
    .catch(err => console.log(err))