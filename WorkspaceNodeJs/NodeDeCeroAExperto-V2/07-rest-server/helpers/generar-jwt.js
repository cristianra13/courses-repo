const jwt = require('jsonwebtoken');

/**
 * Método para crear JWT
 * 
 * @param {}} uid 
 * @returns 
 */
const generarJWT = (uid = '') => {

    return new Promise((resolve, reject) => {

        const payload = { uid };

        jwt.sign(payload, process.env.SECRET_KEY, {
            expiresIn: '5h'
        }, (error, token) => {
            if (error) {
                console.log(error);
                reject('Error al generar token');
            } else {
                resolve(token);
            }
        });


    });
}

module.exports = generarJWT;