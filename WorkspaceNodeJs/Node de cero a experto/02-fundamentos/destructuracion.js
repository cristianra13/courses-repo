// esto es un objeto
let deadpool = {
    nombre: 'Wade',
    apellido: 'Wilson',
    poder: 'Regeneración',
    getNombre: function() {
        return `${this.nombre} ${this.apellido} tiene un poder de ${this.poder}`
    }
};
console.log(deadpool.getNombre());


console.log('\n\n');


// forma comun de extraer atributos del objeto
/* let nombre = deadpool.nombre
let apellido = deadpool.apellido
let poder = deadpool.poder */

/**
 *  desestructuración del objeto, estás deben tener el mismo nombre de los atributos del objeto
 */
let { nombre, apellido, poder } = deadpool;

console.log(nombre, apellido, poder);

/**
 * En caso de NO querer el mismo nombre del atrubuto del objeto
 * Se debe asignar a la variable el valor del atributo con el objeto como se muestra a continuación
 */

let { nombreHeroe = deadpool.nombre, apellidoHeroe = deadpool.apellido, poderHeroe = deadpool.poder } = deadpool;

console.log(nombreHeroe, apellidoHeroe, poderHeroe);