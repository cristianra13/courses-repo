import { HttpClient, HttpErrorResponse, HttpEvent, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from '../models/user.model';
import { JwtHelperService } from "@auth0/angular-jwt";
import { CustomHttpResponse } from '../models/custo-http-response.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public apiUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  public getusers(): Observable<User[] | HttpErrorResponse> {
    return this.http.get<User[]>(`${this.apiUrl}/user/list`);
  }

  /**
   *
   * Con FormData pasamos los campos como llave - valor
   *
   * @param formData
   * @returns
   */
  public addUser(formData: FormData): Observable<User | HttpErrorResponse> {
    return this.http.post<User>(`${this.apiUrl}/user/add`, formData);
  }

  public updateUser(formData: FormData): Observable<User | HttpErrorResponse> {
    return this.http.post<User>(`${this.apiUrl}/user/update`, formData);
  }

  public resetPassword(email: string): Observable<CustomHttpResponse | HttpErrorResponse> {
    return this.http.get<CustomHttpResponse>(`${this.apiUrl}/user/reset-password/${email}`, {

    });
  }

  public updateProfileImage(formData: FormData): Observable<HttpEvent<User> | HttpErrorResponse> {
    return this.http.post<User>(`${this.apiUrl}/user/update-profile-image`, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  /* public deleteUser(userId: number): Observable<CustomHttpResponse | HttpErrorResponse> {
    return this.http.delete<CustomHttpResponse>(`${this.apiUrl}/user/delete/${userId}`);
  } */

  public deleteUser(username: string): Observable<CustomHttpResponse> {
    return this.http.delete<CustomHttpResponse>(`${this.apiUrl}/user/delete/${username}`);
  }

  public addUsersToLocalCache(users: User[]) {
    localStorage.setItem('users', JSON.stringify(users));
  }

  public getUsersFromLocalCache(): User[] {
    if(localStorage.getItem('users')) {
      return JSON.parse(localStorage.getItem('users'));
    }
    return null;
  }

  public createUserFormData(loggedInUsername: string, user: User, profileImage: File): FormData {
    console.log('createUserFormData:', loggedInUsername, user);
    const formData = new FormData();
    formData.append('currentUsername', loggedInUsername);
    formData.append('firstName', user.firstName);
    formData.append('lastName', user.lastName);
    formData.append('username', user.username);
    formData.append('email', user.email);
    formData.append('role', user.role);
    formData.append('profileImage', profileImage);
    formData.append('activeUser', JSON.stringify(this.validateActiveUser(user.activeUser)));
    formData.append('notLockedUser', JSON.stringify(this.validateNotLockedUser(user.notLockedUser)));
    formData.append('profileImage', profileImage);
    console.log(formData);
    return formData;
  }

  private validateActiveUser(activeUser: boolean) {
    if (!activeUser) {
      return false;
    }
    return true;
  }

  private validateNotLockedUser(notLockedUser: boolean) {
    if (!notLockedUser) {
        return false;
    }
    return true;
  }

}
