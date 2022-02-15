import { HttpClient } from "@angular/common/http";
import { Injectable, NgModule } from "@angular/core";
import { UserLogin } from '../models/user-login';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login.response';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseEndpoint = 'http://localhost:8080/login';

  constructor(private http: HttpClient){

  }

  login(userLogin: UserLogin): Observable<LoginResponse>
  {
    return this.http.post<LoginResponse>(this.baseEndpoint, userLogin)
  }

}
