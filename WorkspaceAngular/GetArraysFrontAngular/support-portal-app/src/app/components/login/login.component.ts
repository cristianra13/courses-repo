import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/authentication-service.service';
import { NotificationService } from '../../service/notification.service';
import { User } from '../../models/user.model';
import { Subscription } from 'rxjs';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { NotificationType } from '../../enum/notification-type.enum';
import { HeaderType } from '../../enum/header-type.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  showLoading: boolean;
  private subscriptions: Subscription[] = [];

  constructor(private router: Router,
              private authService: AuthenticationService,
              private notifier: NotificationService) {
  }

  ngOnInit(): void {
    if ( this.authService.isUserLoggedIn() ) {
      this.router.navigateByUrl('/user/management');
    } else {
      this.router.navigateByUrl('/login');
    }
  }

  onLogin(user: User) {
    this.showLoading = true;
    // agregamos la subscripcion al array de subscription
    this.subscriptions.push(
      this.authService.login(user).subscribe( (response: HttpResponse<User>) => {
        // obtenemos el token de los headers
        const token = response.headers.get(HeaderType.JWT_TOKEN);
        this.validateIfExistsToken(token);
        // guardamos el token en el localstorage
        this.authService.saveToken(token);
        // agregamos el usuario al localstorage
        this.authService.addUserToLocalStorage(response.body);
        this.router.navigateByUrl('/user/management');
        this.showLoading = false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.sendErrorNotification(NotificationType.ERROR, errorResponse.error.message);
        this.showLoading = false;
      })
    );
  }

  private sendErrorNotification(type: NotificationType, message: string) {
    if (message) {
      this.notifier.notify(type, message);
    } else {
      this.notifier.notify(type, 'An error ocurred. Please try again.');
    }
  }
  private validateIfExistsToken(token: string) {
    if (!token) {
      this.sendErrorNotification(NotificationType.ERROR, null);
      this.showLoading = false;
      throw new Error('Token is not present on response headers');
    }
  }

  ngOnDestroy(): void {
    // limpiamos los subscriptions
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
