import { Routes } from '@angular/router';
import { LoginService } from './services/login.service';

const APP_ROUTES: Routes = [
  {path: 'login', component: LoginService},
  {path: '**', pathMatch: 'full', redirectTo: 'login'}
];

export class AppRouting{}
