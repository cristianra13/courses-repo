import { Injectable } from '@angular/core';
import { Curso } from '../models/curso';
import { CommonService } from './common.service';
import { HttpClient } from '@angular/common/http';
import { BASE_ENDPOINT } from '../config/app';
import { Alumno } from '../models/alumno';
import { Observable } from 'rxjs';
import { Examen } from '../models/examen';

@Injectable({
  providedIn: 'root'
})
export class CursoService extends CommonService<Curso> {

  // Sobreescribimos la base del endpoint
  protected baseEndpoint = BASE_ENDPOINT + '/cursos';

  constructor(client: HttpClient) {
    super(client);
  }

  asignarAlumnos(curso: Curso, alumnos: Alumno[]): Observable<Curso> {
    return this.client.put<Curso>(`${this.baseEndpoint}/${curso.id}/asignar-alumnos`, alumnos, {headers: this.headers});
  }

  eliminarAlumnoCurso(curso: Curso, alumno: Alumno): Observable<Curso> {
    return this.client.put<Curso>(`${this.baseEndpoint}/${curso.id}/eliminar-alumno`, alumno, {headers: this.headers});
  }

  asignarExamenes(curso: Curso, examenes: Examen[]): Observable<Curso> {
    return this.client.put<Curso>(`${this.baseEndpoint}/${curso.id}/asignar-examen`, examenes, {headers: this.headers});
  }

  eliminarExamen(curso: Curso, examen: Examen): Observable<Curso> {
    return this.client.put<Curso>(`${this.baseEndpoint}/${curso.id}/eliminar-examen`, examen, {headers: this.headers});
  }

  obtenerCursoPorAlumnoId(alumno: Alumno): Observable<Curso> {
    return this.client.get<Curso>(`${this.baseEndpoint}/alumno/${alumno.id}`);
  }
}
