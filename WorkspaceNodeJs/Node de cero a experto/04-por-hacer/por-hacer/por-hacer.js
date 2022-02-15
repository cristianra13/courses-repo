const fs = require('fs')
const colors = require('colors')

let listadoPorHacer = []

const guardarDB = () => {
    // Convertimos un objeto a JSON
    let data = JSON.stringify(listadoPorHacer)

    fs.writeFileSync('data-base-json/data.json', data, (err) => {
        if (err)
            throw new Error('Error al grabar en BD JSON')
    })
}

const cargarDB = () => {
    try {
        listadoPorHacer = require('../data-base-json/data.json')
    } catch (err) {
        // Si no existe nada en el archivo BD JSON, crea un arreglo vacío
        listadoPorHacer = []
    }

}


const crear = (descripcion) => {

    /**
     * Cargamos primero la base de datos y posterior a esto, se agrega el siguiente elemento
     * al arreglo con el crear
     */
    cargarDB()

    let porHacer = {
        descripcion,
        comnpletado: false
    }

    listadoPorHacer.push(porHacer)
    guardarDB()

    return porHacer
}

const getListado = () => {
    cargarDB()
    return listadoPorHacer
}

const actualizar = (descripcion, estado) => {
    // cargamos arreglo en listado por hacer
    cargarDB()

    /**
     * buscar en el arreglo lo que llega por descripción,
     * .findIndex recibe un callback el cual va a hacer un ciclo interno por cada unos de
     * los elementos del arreglo a partir de un valor clave.
     * Si no lo encuentra, retorna -1
     */
    let index = listadoPorHacer.findIndex(tarea => tarea.descripcion === descripcion)

    if (index >= 0) {
        /**
         * Cambiamos el estado por hacer de la posición index y tomamos el atributo completado,
         * y este va a ser igual por lo que llegue como completado en el argumento
         * 
         */
        listadoPorHacer[index].comnpletado = estado
        guardarDB()
        return true
    } else {
        return false
    }

}

const borrar = (descripcion) => {
    cargarDB()

    let nuevoListado = listadoPorHacer.filter(tarea => tarea.descripcion !== descripcion)
        // busca el elemento del index, y como segundo parametro, la cantidad de elementos a borrar con este y despues de este
    if (listadoPorHacer.length === nuevoListado.length) {
        return false
    } else {
        listadoPorHacer = nuevoListado
        guardarDB()
        return true
    }
}

module.exports = {
    crear,
    getListado,
    actualizar,
    borrar
}