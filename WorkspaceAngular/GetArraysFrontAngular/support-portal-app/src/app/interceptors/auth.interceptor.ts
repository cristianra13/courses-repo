import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService) {}

  intercept(httpRequest: HttpRequest<any>, httpHandler: HttpHandler): Observable<HttpEvent<any>> {
    // Si el interceptor encuentra cualquiera de las urls de los if, continua su proceso
    if (httpRequest.url.includes(`${this.authService.apiUrl}/user/login`)) {
      return httpHandler.handle(httpRequest);
    }
    if (httpRequest.url.includes(`${this.authService.apiUrl}/user/register`)) {
      return httpHandler.handle(httpRequest);
    }
    /* if (httpRequest.url.includes(`${this.authService.apiUrl}/user/reset-password`)) {
      return httpHandler.handle(httpRequest);
    } */
    /**
     * Si no se ingresa a ninguno de los anteriores if,
     * quiere decir que el usuario no está realizando ninguna de las
     * anteriores operaciones por lo que se debe agregar el token a
     * los headers
     */
    this.authService.loadToken();
    const token = this.authService.getToken();
    const request = httpRequest.clone({ setHeaders: { Authorization: `Bearer ${token}` } });

    return httpHandler.handle(request);
  }
}
