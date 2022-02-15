// uno de los callbacks mas sencillos que hay
/* setTimeout(() => {
    console.log('Hola mundo');
}, 3000) */

let getUsuarioById = (id, callback) => {
    let usuario = {
        nombre: 'Cristian',
        id // si la propiedad es la misma que se se recibe como parametro, está se puede dejar de tal forma
    }

    // simulación de que un id no existe
    if (id === 20) {
        callback(`El usuario con id ${id} no existe en la base de datos`)
    } else {
        callback(null, usuario)
    }

}

// es muy común que el primer parametro de un callback sea el error
getUsuarioById(10, (err, usuario) => {
    if (err) {
        return console.log(err);
    }
    console.log('Usuario de base de datos', usuario);
});