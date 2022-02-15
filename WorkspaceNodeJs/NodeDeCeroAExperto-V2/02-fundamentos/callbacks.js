// loas callbacks son funciones que se mandan como argumento a una funcion

const getUsuarioById = (id, callback) => {
    usuario = {
        id,
        nombre: 'Cristian'
    }

    setTimeout(() => {
        callback(usuario)
    }, 1500);
}

// recibimos el usuario en el callback
getUsuarioById(10, (usuario) => {
    console.log(usuario);
})