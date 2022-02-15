import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import { PersonaComponent } from './persona/persona.component';
import { FormularioComponent } from './formulario/formulario.component';
import {LogginServiceService} from './LogginService.service';
import {PersonasService} from './personas.service';

@NgModule({
  declarations: [
    AppComponent,
    PersonaComponent,
    FormularioComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [
    LogginServiceService,
    PersonasService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
