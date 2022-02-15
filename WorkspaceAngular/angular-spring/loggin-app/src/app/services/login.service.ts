import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService
{

  url = '/login/login';

  constructor(private http: HttpClient)
  {

  }



  login(usuario: Usuario)
  {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      // 'Authorization': this.basic
    });
      let options = { headers: headers };


    return this.http.post(this.url, JSON.stringify(usuario), options);
  }

  consulta()
  {
    return this.http.get<string>(this.url);
  }
}

/* const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
} */
