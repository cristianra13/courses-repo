import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { DestinoViajeComponent } from './destino-viaje/destino-viaje.component';
import { ListaDestinosComponent } from './lista-destinos/lista-destinos.component';
import { DestinoDetalleComponent } from './destino-detalle/destino-detalle.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { FormDestinoViajeComponent } from './form-destino-viaje/form-destino-viaje.component';
import { HomeComponent } from './home/home.component';

// Declaramos las rutas que se van renderear cuando las agreguemos en el navegador
const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: ListaDestinosComponent},
  {path: 'destino', component: DestinoDetalleComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    DestinoViajeComponent,
    ListaDestinosComponent,
    DestinoDetalleComponent,
    FormDestinoViajeComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes), // registramos las rutas en el modulo
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
