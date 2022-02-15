const axios = require('axios');

const getLugarLatLong = async(direccion) => {

    const encoded = encodeURI(direccion);

    const instance = axios.create({
        baseURL: `https://geocode.xyz/Hauptstr.,+57632+"${encoded}"?json=1`,
        timeout: 5000,
        // headers: { 'X-Custom-Header': 'foobar' } // los headers por si son necesarios
    });

    const respuesta = await instance.get();
    /* .then(resp => console.log(resp.data))
    .catch(err => {
        console.log('ERROR ', err);
    }); */

    if (respuesta.error) {
        throw new Error(`No hay resultados para el argumento de busqueda especificado: ${ direccion }`)
    }

    const direc = `${respuesta.data.standard.city} - ${respuesta.data.standard.countryname}`;
    const latitud = respuesta.data.latt;
    const longitud = respuesta.data.longt;

    return {
        direc,
        latitud,
        longitud
    }
}

module.exports = {
    getLugarLatLong
}