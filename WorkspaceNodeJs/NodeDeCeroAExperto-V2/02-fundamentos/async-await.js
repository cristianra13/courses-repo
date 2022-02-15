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

const id = 3;

const getInfoUsuario = async() => {

    try {
        const empleado = await getEmpleado(id);
        const salario = await getSalario(id);

        return `El salario del empleado ${empleado.nombre} es de ${salario.salario}`
    } catch (error) {
        throw error;
    }



}

getInfoUsuario()
    .then(r => console.log(r))
    .catch(err => console.log(err))