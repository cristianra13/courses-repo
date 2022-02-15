import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(private auth: AuthService, private router: Router)
  {

  }

  /**
   *
   * @param next es la siguiente ruta a la que el usuario quiere navegar
   * @param state es el estado de la ruta
   */
  canActivate(): boolean
  {
    if (this.auth.estaAutenticado())
    {
      return true;
    }
    else
    {
      this.router.navigateByUrl('login');
      return false;
    }
  }

}
