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
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {

  showLoading: boolean;
  private subscriptions: Subscription[] = [];

  constructor(private router: Router,
              private authService: AuthenticationService,
              private notifier: NotificationService) {
  }

  ngOnInit(): void {
    if ( this.authService.isUserLoggedIn() ) {
      this.router.navigateByUrl('/user/management');
    }
  }

  onRegister(user: User) {
    // agregamos la subscripcion al array de subscription
    this.subscriptions.push(
      this.authService.register(user).subscribe( (response: User) => {
        this.showLoading = false;
        this.sendNotification(NotificationType.SUCCESS, `A new account was created for ${response.firstName}.\nPlease check your email for password to login`);
      },
      (errorResponse: HttpErrorResponse) => {
        console.log(errorResponse);
        this.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        this.showLoading = false;
      })
    );
  }

  private sendNotification(type: NotificationType, message: string) {
    if (message) {
      this.notifier.notify(type, message);
    } else {
      this.notifier.notify(type, 'An error ocurred. Please try again.');
    }
  }

  ngOnDestroy(): void {
    // limpiamos los subscriptions
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
