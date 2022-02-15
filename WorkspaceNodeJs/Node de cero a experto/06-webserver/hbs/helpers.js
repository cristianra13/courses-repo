const hbs = require('hbs');


/**
 * Helpers HBS es una función que se dispara cuando sea solicitada por el template
 * Lo que hace es que busca una variable dentro de la función que tenga la variable que solicitam, en este caso, getAnio
 * si no la encuenta, vienen a bsucar en los helpers si está existe
 */
hbs.registerHelper('getAnio', () => new Date().getFullYear());


hbs.registerHelper('capitalizar', (texto) => {
    let palabras = texto.split(' ')
    palabras.forEach((palabra, index) => {
        palabras[index] = palabra.charAt(0).toUpperCase() + palabra.slice(1).toLowerCase()
    });

    return palabras.join(' ')
});