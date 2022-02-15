export class Persona
{
  /*nombre: string;
  apellido: string;

  constructor(nombre: string, apellido: string)
  {
    this.nombre = nombre;
    this.apellido = apellido;
  }*/

  // sintaxis reducida, se crean las variables y se asignan de una vez con public dentro del constructor
  constructor(public nombre: string, public apellido: string) {}
}
