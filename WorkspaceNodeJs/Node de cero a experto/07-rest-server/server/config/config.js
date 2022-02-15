/**
 * Puerto
 */
process.env.PORT = process.env.PORT || 3000;

/**
 * Entorno
 * 
 * Si la variable existem, es porque estamos en produccion
 */
process.env.NODE_ENV = process.env.NODE_ENV || 'DEV';

/**
 * Fecha expiración token
 * va en formato segundos * minutos * horas * dias
 * en este caso, solo segundos por minutos
 */
// process.env.EXP_DATE = 60 * 60 * 24 * 30;
process.env.EXP_DATE = '24h';

/**
 * seed o semilla o clave secreta del token
 * de está forma nadie sabe cual es la semilla del token ya que se convierte e una variable 
 * de entorno en heroku
 */
process.env.SEED = process.env.SEED || 'esta-es-la-clave-secreta-del-token-jwt';

/**
 * Base de datos
 */

let urlDB;

if (process.env.NODE_ENV === 'DEV') {
    urlDB = 'mongodb://localhost:27017/cafe';
} else {
    /**
     * Usamos la variable de entorno creada en heroku:
     * $ heroku config:set MONGO_URI="mongodb+srv://userdb-cafe:fn1okCEPqFHi4wwq@cluster0.pvaos.mongodb.net/cafe?retryWrites=true&w=majority"
     */
    urlDB = process.env.MONGO_URI;
}

process.env.URLDB = urlDB;

/**
 * Google CLient ID
 */
process.env.CLIENT_ID = process.env.CLIENT_ID || '880010084777-brdh9sk2remu2954o8i41uvn0gs3l9qu.apps.googleusercontent.com';