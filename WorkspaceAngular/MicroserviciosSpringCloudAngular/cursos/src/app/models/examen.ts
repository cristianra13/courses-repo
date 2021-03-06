import { Pregunta } from './pregunta';
import { Asignatura } from './asignatura';
import { Generic } from './generic';

export class Examen implements Generic {

  id: number;
  nombre: string;
  createAt: string;
  preguntas: Pregunta[] = [];
  asignaturaHija: Asignatura;
  asignaturaPadre: Asignatura;
  respondido: boolean;
}
