// Desestructuracion

const deadpool = {
    nombre: 'Wade',
    apellido: 'Wilson',
    poder: 'Regeneración',
    edad: 40,
    getNombre() { return `Nombre: ${this.nombre}, apellido: ${this.apellido} y poder: ${this.poder}` }
}

console.log(deadpool.getNombre());

/* const nombre = deadpool.nombre;
const apellido = deadpool.apellido;
const poder = deadpool.poder; */

// desestructuración
/* const { nombre, apellido, poder, edad = 0 } = deadpool; */


// console.log(nombre, apellido, poder, edad);
console.log('--------------');

// desestructuración de un objeto como argumento
function imprimeHeroe({ nombre, apellido, poder, edad = 0 }) {
    // const { nombre, apellido, poder, edad = 0 } = heroe;
    console.log(nombre, apellido, poder, edad);
}

//imprimeHeroe(deadpool);

const heroes = ['Deadpool', 'Wolverine', 'Xavier', 'Otro perro'];

// desestructurar un arreglo
const [, h2, h3] = heroes;
console.log(h2, h3);