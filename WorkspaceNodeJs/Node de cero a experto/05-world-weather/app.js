// importamos la función que necesitamos
const lugar = require('./lugar/lugar')
const clima = require('./clima/clima')

const argv = require('yargs').options({
    direccion: {
        alias: 'd',
        description: 'Dirección de la ciudad para obtener el clima',
        demand: true
    }
}).argv;

// Esto regresa una promesa por el async con que se anoto la función
/* lugar.getLugarLatLong(argv.direccion)
    .then(console.log) */

/* clima.getClima(4.60769, -74.11439)
    .then(console.log)
    .catch(console.log) */

const getInfo = async(direccion) => {

    try {
        const coords = await lugar.getLugarLatLong(direccion);
        const temp = await clima.getClima(coords.latitud, coords.longitud);

        return `El clima de ${coords.direc} es de ${temp}°C.`;
    } catch (err) {
        return `No se pudo determinar el clima de ${direccion}.`;
    }
}

getInfo(argv.direccion)
    .then(console.log)
    .catch(console.log)