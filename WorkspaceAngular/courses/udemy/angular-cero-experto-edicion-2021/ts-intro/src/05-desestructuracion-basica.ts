interface Reproductor {
  volumen: number;
  segundo: number;
  cancion: string;
  detalles: Detalles
}

interface Detalles {
  autor: string;
  anio: number;
}

const reproductor: Reproductor = {
  volumen: 90,
  segundo: 36,
  cancion: 'Mess',
  detalles: {
    autor: 'Ed Sheeran',
    anio: 2015
  }
}

// Desestructuración
// Un objeto se desestructura con  {}
// const {volumen, segundo, cancion, detalles: {autor: autorDetalle}} = reproductor;

const {volumen, segundo, cancion, detalles} = reproductor;
const {autor} = detalles;

console.log(`El volumen actual es de: ${volumen}`)
console.log(`El segundo actual es de: ${segundo}`)
console.log(`La canción actual es de: ${cancion}`)
console.log(`El autor es: ${autor}`)


// Desestructuración de Arreglos
// Un Arreglo se desestructura con  []
// Acá el nombr eno importa, pero si la posición
const dbz: string[] = ['Goku', 'Vegeta', 'Trunks'];
const [goku, vegeta, personaje3] = dbz;

console.log('Personaje 1:', goku)
console.log('Personaje 1:', vegeta)
console.log('Personaje 1:', personaje3)

const [,,p3] = dbz;
console.log(p3)