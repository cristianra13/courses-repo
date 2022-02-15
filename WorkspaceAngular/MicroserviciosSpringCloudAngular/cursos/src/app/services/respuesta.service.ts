import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Respuesta } from '../models/respuesta';
import { Observable } from 'rxjs';
import { BASE_ENDPOINT } from '../config/app';
import { Alumno } from '../models/alumno';
import { Examen } from '../models/examen';

@Injectable({
  providedIn: 'root'
})
export class RespuestaService  {

  private baseEndpoint = BASE_ENDPOINT + '/respuestas';
  protected headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private client: HttpClient) {

  }

  crear(respuestas: Respuesta[]): Observable<Respuesta[]> {
    return this.client.post<Respuesta[]>(this.baseEndpoint, respuestas, {headers: this.headers});
  }

  obtenerRespuestasPorAlumnoPorExamen(alumno: Alumno, examen: Examen): Observable<Respuesta>{
    return this.client.get<Respuesta>(`${this.baseEndpoint}/alumno/${alumno.id}/examen/${examen.id}`);
  }

}
