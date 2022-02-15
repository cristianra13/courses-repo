// Callbacks dentro de otros callbacks

const empleados = [{
        id: 1,
        nombre: 'Cristian'
    },
    {
        id: 2,
        nombre: 'Lynda'
    },
    {
        id: 3,
        nombre: 'Maria'
    }
];


const salarios = [{
        id: 1,
        salario: 1000
    },
    {
        id: 2,
        salario: 1500
    }
];

/**
 * La promesa recibe dos argumentos que son el resolve y reject
 */

const getEmpleado = (id) => {
    const empleado = empleados.find(emp => emp.id === id);

    return new Promise((resolve, reject) => (empleado) ? resolve(empleado) : reject(`No existe empleado con id: ${id}`));

}

const getSalario = (id) => {
    const salario = salarios.find(sal => sal.id === id);
    return new Promise((resolve, reject) => (salario) ? resolve(salario) : reject(`No existe salario para el id: ${id}`));
}

/**
 * En caso de que la promesa se resuelva, ingresa por el .yhen
 */
const id = 10;

// está sería la parte fea
/* getEmpleado(id)
    .then(empleado => {
        getSalario(id)
            .then(salario => console.log(empleado.nombre, salario.salario))
            .catch(err => console.log(err))
    })
    .catch(err => console.log(err)); */

// promesas en cadena
/**
 * El return es obligatorio para encadenar las promesas
 */

let nombre;

getEmpleado(id)
    .then(empleado => {
        nombre = empleado;
        return getSalario(id);
    })
    .then(salario => console.log(`El empleado ${nombre.nombre} tiene como salario ${salario.salario}`))
    .catch(err => console.log(err));