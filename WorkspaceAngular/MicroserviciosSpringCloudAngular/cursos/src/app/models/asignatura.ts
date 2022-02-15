
export class Asignatura {
  id: number;
  nombre: string;
  asignaturaPadre: Asignatura;
  asignaturasHijos: Asignatura[] = [];
}
