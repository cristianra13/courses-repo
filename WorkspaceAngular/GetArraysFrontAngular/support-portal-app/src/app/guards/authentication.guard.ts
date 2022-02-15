import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication-service.service';
import { NotificationService } from '../service/notification.service';
import { NotificationType } from '../enum/notification-type.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private authService: AuthenticationService,
              private router: Router,
              private notificationService: NotificationService) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) {
    return this.isUserLoggedIn();
  }

  private isUserLoggedIn(): boolean {
    if ( this.authService.isUserLoggedIn() ) {
      return true;
    }
    this.router.navigate(['/login']);
    this.notificationService.notify(NotificationType.ERROR, 'You need to login to acces this page');
    return false;
  }

}
