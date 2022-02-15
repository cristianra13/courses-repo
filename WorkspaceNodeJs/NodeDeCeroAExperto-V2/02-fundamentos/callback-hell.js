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

const getEmpleado = (id, callback) => {
    const empleado = empleados.find(emp => emp.id === id);

    if (empleado) {
        callback(null, empleado.nombre);
    } else {
        callback(`Empleado ${id} no existe`, null);
    }

}

const getSalario = (id, callback) => {
    const salario = salarios.find(sal => sal.id === id); // podriamos hacer salarios.find(sal => sal.id === id)?.salario;

    if (salario) {
        callback(null, salario);
    } else {
        callback(`No existe un salario para el id ${id}`)
    }
}

const idUsuario = 3;

getEmpleado(idUsuario, (err, empleado) => {
    if (err) {
        return console.log(err);
    }

    getSalario(idUsuario, (err, salario) => {
        if (err) {
            return console.log(err);
        }
        console.log(`El empleado ${empleado} tiene como salario: ${salario.salario}`);
    });

});