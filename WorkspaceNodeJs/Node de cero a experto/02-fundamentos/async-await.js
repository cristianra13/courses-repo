/**
 * Async - Await
 */

let getNombre = async() => {

    // throw new Error('No existe un nombre para ese usuario')

    return 'Cristian'
}

console.log(getNombre());

getNombre().then(nombre => {
        console.log(nombre)
    })
    .catch(err => {
        /**
         * Cualquier error que sea disparado en la función getNombre
         * Será capturado en este catch
         */
        console.log('Error de Async', err);
    })

console.log('');

/**
 * La unica condición de que se use un await,
 * es que la función este marcada con async
 */

let saludo = async() => {

    /**
     * Esto hace que las funciones que regresan promesas, 
     * tengan la sensación que son trabajos sincronos
     * 
     * Javascript no continua con la ejecución de la fuunción, hasta que no obtenga la respuesta respectiva
     * en este caso, la respuesta de getNombre
     */
    let nombre = await getNombre()
    return `Hola ${ nombre }`
}

let getNombre1 = () => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve('Cristian Real')
        }, 3000);


    });
}

console.log('');

saludo().then(mensaje => {
    // esto será el producto del llamado a la función saludo()
    console.log(mensaje);
})