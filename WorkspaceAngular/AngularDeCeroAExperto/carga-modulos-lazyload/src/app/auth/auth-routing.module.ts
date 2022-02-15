import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { LoginComponent } from './pages/login/login.component';
import { RegistroComponent } from './pages/registro/registro.component';

const routes: Routes = [
  {
     path: '',
    children: [
      { path: 'forgot', component: ForgotPasswordComponent},
      { path: 'login', component: LoginComponent},
      { path: 'registro', component: RegistroComponent},
      {path: '**', redirectTo: 'login'}
    ]
  }
];


@NgModule({
  imports: [
    // de esta manera ya que son rutas hijas, las principales estan en el app-routing.module
    RouterModule.forChild(routes)
  ]
})
export class AuthRoutingModule { }
