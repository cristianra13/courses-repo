/**
 * Cuando se usan variables con var, estás se puede declarar, reutilizar, reinicializar, renombrar N cantidad de veces
 */

var nombre = 'wolverine'

if (true) {
    var nombre = 'magneto'
}

console.log(nombre)

/**
 * Al usar una variable con let solo se pued declara una sola vez la variable
 */

let nombre2 = 'wolverine'

if (true) {
    /**
     * acá se permite declarar la variable nombre2 ya que está es solamente local,
     * Quiere decir que solo va a poder ser usada dentro de la clausula if
     */
    let nombre2 = 'magneto'
}

console.log(nombre2)

/**
 * Como i ya fue inicializada con var dentro del ciclo
 * al momento de terminar está queda con 6 por lo que no ingresa mas al ciclo
 * pero se imprime su valor por fuera
 */
for (var i = 0; i <= 5; i++) {
    console.log(`i: ` + i)
}

console.log(i);

/**
 * En este caso se imprime j la cantidad de veces dentro del ciclo.
 * pero al tratar de imprimirlo afuera del ciclo, este genera error ya que la variable solo está permitida para
 * usar denntro del ciclo y no fuera, afuera es una variable inexistente
 */
for (let j = 0; j <= 5; j++) {
    console.log(`j: ` + j)
}

// console.log(j); // Éstá linea genera error
console.log('-------');


let j;
for (let j = 0; j <= 5; j++) {
    console.log(`j: ` + j)
}
/**
 * Acá en está impresión se genera un undefined, a que la variable l¿no ha sido inicializada
 */
console.log(j);