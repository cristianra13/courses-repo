require('dotenv').config();

const { leerInput, pausa, inquirerMenu, listarCiudades } = require("./helpers/inquirer");
const Busquedas = require("./models/busquedas");


console.clear();

const main = async() => {

    console.log(process.env.OPEN_WEATHER_KEY);
    let opt;
    const busquedas = new Busquedas();

    do {
        opt = await inquirerMenu();

        switch (opt) {
            case 1:
                // obtener mensaje
                const ciudad = await leerInput('Nombre de la ciudad:');
                const ciudades = await busquedas.buscarCiudad(ciudad);

                const idSeleccionado = await listarCiudades(ciudades);

                if (idSeleccionado === '0') {
                    continue;
                }

                const ciudadSel = ciudades.find(lugar => lugar.id === idSeleccionado);

                // guardar en Db
                busquedas.agregarHistorial(ciudadSel.nombre);

                // clima
                const clima = await busquedas.climaCiudad(ciudadSel.lat, ciudadSel.lng);

                console.clear();
                console.log('\nInformación de la ciudad\n'.green);
                console.log('Ciudad:', ciudadSel.nombre);
                console.log('Lat:', ciudadSel.lat);
                console.log('Lon:', ciudadSel.lng);
                console.log('Temp:', clima.temp);
                console.log('Mínima:', clima.max);
                console.log('Máxima:', clima.min);
                console.log('Estado del clima:', clima.desc.green);
                break;
            case 2:
                busquedas.historialCapitalizado.forEach((lugar, i) => {
                    const idx = `${i + 1}`.green;
                    console.log(`${idx} ${lugar}`);
                })
                break;
            case 0:
                break;
        }

        // hacemos la pausa para ver el menú
        if (opt !== 0) await pausa();

    } while (opt !== 0);

}

main();