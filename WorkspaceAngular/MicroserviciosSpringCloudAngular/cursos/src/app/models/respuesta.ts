import { Alumno } from './alumno';
import { Pregunta } from './pregunta';

export class Respuesta {
  id: string;
  respuesta: string;
  alumno: Alumno;
  pregunta: Pregunta;
}
