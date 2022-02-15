let nombre = 'Deadpool'
let real = 'Wade Winston'

console.log(nombre + ' ' + real);

console.log(`${nombre} - ${real}`);

let nombreCompleto = nombre + ' ' + real
    // lo mismo de:
let nombretemplate = `${nombre} ${real}`;

console.log(nombreCompleto === nombretemplate);

console.log('\n\n');

function getNombre() {
    return `${nombre} es ${real}`;
}

console.log(`El nombre de: ${getNombre()}`);