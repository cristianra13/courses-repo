// Funciones basicas

function sumar(a: number, b: number) {
  return a + b;
}

const restar = (a: number, b: number) => a - b;

const resultado = sumar(10, 20);

console.log(resultado);


const resultadoResta = restar(100, 40);

console.log(resultadoResta);

function multiplicar(numero: number, otroNumero?: number, base: number = 2): number {
  return numero * base;
}

interface PersonajeLOR {
  nombre: string;
  pv: number;
  mostrarHp: () => void;
}

function curar(personaje: PersonajeLOR, curarX): void {
  personaje.pv += curarX;
}

const nuevoPersonaje: PersonajeLOR = {
  nombre: 'Cristian',
  pv: 50,
  mostrarHp() {
    console.log('Puntos de vida:', this.pv)
  }
}

curar(nuevoPersonaje, 25);
nuevoPersonaje.mostrarHp();

// const resultadoMul = multiplicar(8, 3);
// console.log(resultadoMul)