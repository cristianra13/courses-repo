interface Pasajero {
  nombre: string;
  hijos?: string[];
}

const pasajero1: Pasajero = {
  nombre: 'Cristian'
}

const pasajero2: Pasajero = {
  nombre: 'juana',
  hijos: ['hanna', 'maria']
}

function imprimeHijos(pasajero: Pasajero): void {
  const cuantoshijos = pasajero.hijos?.length || 0; // si no hay nada en hijos, agrega 0
  console.log(cuantoshijos);
}

imprimeHijos(pasajero1);