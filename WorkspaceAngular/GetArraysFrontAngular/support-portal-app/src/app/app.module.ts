import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
//import { AuthInterceptor } from './interceptors/auth.interceptor';
import { NotificationModule } from './notification.module';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthInterceptor } from './interceptors/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NotificationModule,
    RouterModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true } // multi: true -> crea multiples intancias del interceptor
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
