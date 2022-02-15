import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from '../models/user.model';
import { JwtHelperService } from "@auth0/angular-jwt";


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public apiUrl: string
  private token: string;
  private loggedInUsername: string;
  private jwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiUrl;
  }

  public login(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(`${this.apiUrl}/user/login`, user, {observe: 'response'});
  }

  public register(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user/register`, user);
  }

  public logout() {
    this.token = null;
    this.loggedInUsername = null;
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    localStorage.removeItem('users');
  }

  public saveToken(token: string) {
    this.token = token;
    localStorage.setItem('token', token);
  }

  public addUserToLocalStorage(user: User) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUserFromLocalCache(): User {
    return JSON.parse(localStorage.getItem('user'));
  }

  public loadToken() {
    this.token = localStorage.getItem('token');
  }

  public getToken(): string {
    return this.token;
  }

  public isUserLoggedIn(): boolean {
    this.loadToken();
    if (this.token) {
      if (this.jwtHelperService.decodeToken(this.token).sub) {
        if ( !this.jwtHelperService.isTokenExpired(this.token) ) {
          this.loggedInUsername = this.jwtHelperService.decodeToken(this.token).sub;
          return true;
        }
      }
    } else {
      this.logout();
      return false;
    }
  }

}
