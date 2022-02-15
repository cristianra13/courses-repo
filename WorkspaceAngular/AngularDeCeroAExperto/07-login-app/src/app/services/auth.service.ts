import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { UsuarioModel } from '../models/usuario.model';

import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthService
{

  // crear nuevo usuario
  // https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]

  // login
  // https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=[API_KEY]


  private url = 'https://identitytoolkit.googleapis.com/v1/accounts:';
  private apiKey = 'AIzaSyArKjS3d6oP4_2EHtl4hNtbiP27-Xtd3sc';
  userToken: string;

  constructor(private http: HttpClient)
  {
    this.leerToken()
  }

  logout()
  {
    localStorage.removeItem('token');
  }

  login(usuario: UsuarioModel)
  {
    const authData =
    {
      ...usuario, // sinonimo de lo anterior escrito
      returnSecureToken: true
    };

    return this.http.post(
      `${this.url}${complementoUrl.login}${this.apiKey}`, authData).pipe(
        map( response => {
          this.guardarToken(response['idToken']);
          return response;
        } )
      );

  }

  registrarNuevoUsuario(usuario: UsuarioModel)
  {
    const authData = {
      /* email: usuario.email,
      password: usuario.password, */
      ...usuario, // sinonimo de lo anterior escrito
      returnSecureToken: true
    };

    return this.http.post(
      `${this.url}${complementoUrl.crearUsuario}${this.apiKey}`, authData
    ).pipe(
      map( response => {
        this.guardarToken(response['idToken']);
        return response;
      } )
    );
  }

  private guardarToken(idToken: string)
  {
    this.userToken = idToken;
    localStorage.setItem('token', idToken);

    let hoy = new Date();
    hoy.setSeconds( 3600 );
    localStorage.setItem('expira', hoy.getTime().toString());
  }

  leerToken()
  {
    if(localStorage.getItem('token'))
    {
      this.userToken = localStorage.getItem('token');
    }
    else
    {
      this.userToken = '';
    }
    return this.userToken;
  }

  estaAutenticado(): boolean
  {
    if (this.userToken.length < 2 )
    {
      return false;
    }

    const expira = Number(localStorage.getItem('expira'));
    const expiraDate = new Date();
    expiraDate.setTime(expira);

    if(expiraDate > new Date())
    {
      return true;
    }
    else
    {
      return false;
    }
  }

}

const complementoUrl = {
  crearUsuario: 'signUp?key=',
  login: 'signInWithPassword?key='
};
