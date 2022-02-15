import { Injectable } from '@angular/core';
import { Examen } from '../models/examen';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Asignatura } from '../models/asignatura';

@Injectable({
  providedIn: 'root'
})
export class ExamenService extends CommonService<Examen> {

  // Sobreescribimos la base del endpoint
  protected baseEndpoint = BASE_ENDPOINT + '/examenes';

  constructor(client: HttpClient) {
    super(client);
  }

  findAllAsignaturas(): Observable<Asignatura[]>{
    return this.client.get<Asignatura[]>(`${this.baseEndpoint}/asignaturas`);
  }

  filtarPorNombre(nombre: string): Observable<Examen[]> {
    return this.client.get<Examen[]>(`${this.baseEndpoint}/buscar/${nombre}`);
  }

}
