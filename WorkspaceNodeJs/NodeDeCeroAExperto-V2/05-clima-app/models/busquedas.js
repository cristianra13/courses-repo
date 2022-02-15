const fs = require('fs');

const axios = require('axios');

class Busquedas {

    historial = [];
    dbPath = './db/database.json'

    constructor() {

        this.leerDB();
    }

    get historialCapitalizado() {
        return this.historial.map(lugar => {
            let palabras = lugar.split(' ');
            palabras = palabras.map(pa => pa[0].toUpperCase() + pa.substring(1))
            return palabras.join(' ');
        })

    }

    get paramsMapBox() {
        return {
            'access_token': process.env.MAPBOX_KEY,
            'limit': 5,
            'language': 'es'
        };
    }

    get paramsWeather() {
        return {
            'appid': process.env.OPEN_WEATHER_KEY,
            'units': 'metric',
            'lang': 'es'
        };
    }

    async buscarCiudad(ciudad = '') {

        try {

            const instance = axios.create({
                baseURL: `https://api.mapbox.com/geocoding/v5/mapbox.places/${ ciudad }.json`,
                params: this.paramsMapBox
            });

            const resp = await instance.get();

            return resp.data.features.map(lugar => ({
                id: lugar.id,
                nombre: lugar.place_name,
                lng: lugar.center[0],
                lat: lugar.center[1]
            }));

        } catch (error) {
            return [];
        }


        // return this.historial.filter(ciu => ciu === ciudad); // retornar las ciudades o lugares que coincidan con el criterio
    }

    async climaCiudad(lat, lon) {
        try {
            // instance axios
            const instance = axios.create({
                baseURL: `http://api.openweathermap.org/data/2.5/weather?`,
                params: {...this.paramsWeather, lat, lon }
            });

            const resp = await instance.get();
            const { weather, main } = resp.data;

            return {
                desc: weather[0].description,
                min: main.temp_min,
                max: main.temp_max,
                temp: main.temp
            }

        } catch (error) {
            console.log(error);
        }
    }


    agregarHistorial(lugar = '') {
        if (this.historial.includes(lugar.toLocaleLowerCase)) {
            return;
        }

        this.historial = this.historial.splice(0, 5);

        this.historial.unshift(lugar.toLowerCase());

        // grabar en DB
        this.guardarDB();
    }

    guardarDB() {
        const payload = {
            historial: this.historial
        }
        fs.writeFileSync(this.dbPath, JSON.stringify(payload));
    }

    leerDB() {
        if (!fs.existsSync(this.dbPath)) {
            return;
        }

        const inforDb = fs.readFileSync(this.dbPath, { encoding: 'utf-8' });
        const data = JSON.parse(inforDb);
        this.historial = data.historial;
    }

}

module.exports = Busquedas;