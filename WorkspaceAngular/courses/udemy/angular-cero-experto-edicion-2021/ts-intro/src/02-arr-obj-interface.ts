let habilidades: string[] = ['Bash', 'Counter', 'Healing'];
// Esto le decimos que hacepta esos tres tipos de dato
let otrasHabilidades: (boolean | string | number)[] = ['Volar', true, 1]; // no es buena practica


// Objetos
interface Personaje {
  nombre: string;
  hp: number;
  habilidades: string[];
  pueblonatal?: string;
}


const personaje: Personaje = {
  nombre: 'Cristian',
  hp: 100,
  habilidades: ['Bash', 'Counter', 'Healing']
}


personaje.pueblonatal = 'Pueblo Paleta'

console.table(personaje)