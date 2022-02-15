import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// componentes
import { RegistroComponent } from './pages/registro/registro.component';
import { LoginComponent } from './pages/login/login.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { AuthRoutingModule } from './auth-routing.module';



@NgModule({
  declarations: [
    RegistroComponent,
    LoginComponent,
    ForgotPasswordComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule
  ]
})
export class AuthModule { }
