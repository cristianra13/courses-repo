import { HttpClient, HttpClientModule, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService extends CommonService<Alumno> {

  // Sobreescribimos la base del endpoint
  protected baseEndpoint = BASE_ENDPOINT + '/alumnos';

  constructor(client: HttpClient){
    super(client);
  }

  crearConFoto(alumno: Alumno, archivo: File): Observable<Alumno>{
    const formData = new FormData();
    formData.append('archivo', archivo);
    formData.append('nombre', alumno.nombre);
    formData.append('apellido', alumno.apellido);
    formData.append('email', alumno.email);
    return this.client.post<Alumno>(`${this.baseEndpoint}/crear-con-foto`, formData);
  }

  editarConFoto(alumno: Alumno, archivo: File): Observable<Alumno>{
    const formData = new FormData();
    formData.append('archivo', archivo);
    formData.append('nombre', alumno.nombre);
    formData.append('apellido', alumno.apellido);
    formData.append('email', alumno.email);
    return this.client.put<Alumno>(`${this.baseEndpoint}/editar-con-foto/${alumno.id}`, formData);
  }

  buscar(texto: string): Observable<Alumno[]> {
    return this.client.get<Alumno[]>(`${this.baseEndpoint}/buscar/${texto}`);
  }
}
