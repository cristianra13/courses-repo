function sumar(a, b) {
    return a + b
}

console.log(sumar(3, 6));

/**
 * La misma funcion anterior pero en este caso usando funcion de flecha o lambda
 * 
 * Dentro del parentesis van los argunmentos y despues de la flecha, va el retorno de la función
 */

let suma = (a, b) => a + b;


console.log(sumar(12, 18));

console.log('');

let saludar = () => 'Hola mundo'

console.log('Hola mundo')

console.log('');

/**
 * Otra forma de hacerlo,
 * Cuando la función tiene como parametro un solo argumento, se podría omitir el parentesis
 */

let saludo = nombre => `Hola ${nombre}`;
console.log('Cristian');

console.log('');

// esto es un objeto
let deadpool = {
    nombre: 'Wade',
    apellido: 'Wilson',
    poder: 'Regeneración',
    /**
     * Tener en cuenta que al usar this de está forma,
     * es para apuntar al valor de la variable fuera de la funci´´on de getNombre()
     */
    getNombre() { return `${this.nombre} ${this.apellido} tiene un poder de ${this.poder}` }

};

console.log(deadpool.getNombre());