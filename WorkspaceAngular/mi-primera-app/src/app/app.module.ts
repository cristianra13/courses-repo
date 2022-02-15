import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { PersonasComponent } from './personas/personas.component';

import { AppComponent } from './app.component';
import { PersonaComponent } from './persona/persona.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    /* Acá se registra la clase PersonasComponent y hacer el import respectivo*/
    AppComponent,
    PersonasComponent,
    PersonaComponent
  ],
  imports: [
    BrowserModule,
    // Este es para trabajar two-way binding
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
