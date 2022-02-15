import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { AuthenticationGuard } from './guards/authentication.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user/management', component: UserComponent, canActivate: [AuthenticationGuard] }, // protegemos la ruta para que se solicite el login
  { path: '**', pathMatch: 'full', redirectTo: '/login' },
]


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
