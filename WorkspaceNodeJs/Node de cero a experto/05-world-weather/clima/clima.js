const axios = require('axios');

const getClima = async(lat, lng) => {
    const resp = await axios.get(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=026377f342d8f69a82dc371b1a8b1bd2&units=metric`);

    return resp.data.main.temp;
}

module.exports = {
    getClima
}