import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';

import { AppComponent } from './app.component';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { ExamenesComponent } from './components/examenes/examenes.component';

import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AlumnosFormComponent } from './components/alumnos/alumnos-form/alumnos-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CursoFormComponent } from './components/cursos/curso-form/curso-form.component';
import { ExamenesFormComponent } from './components/examenes/examenes-form/examenes-form.component';
import { AsignarAlumnosComponent } from './components/cursos/asignar-alumnos/asignar-alumnos.component';
import { AsignarExamenesComponent } from './components/cursos/asignar-examenes/asignar-examenes.component';
import { ResponderExamenComponent } from './components/alumnos/responder-examen/responder-examen.component';
import { ResponderExamenModalComponent } from './components/alumnos/responder-examen-modal/responder-examen-modal.component';
import { VerExamenModalComponent } from './components/alumnos/ver-examen-modal/ver-examen-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    AlumnosComponent,
    CursosComponent,
    ExamenesComponent,
    NavbarComponent,
    AlumnosFormComponent,
    CursoFormComponent,
    ExamenesFormComponent,
    AsignarAlumnosComponent,
    AsignarExamenesComponent,
    ResponderExamenComponent,
    ResponderExamenModalComponent,
    VerExamenModalComponent
  ],
  entryComponents:[
    // modales para cargar de forma dinamica dentro de una vista
    ResponderExamenModalComponent,
    VerExamenModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatCardModule,
    MatTabsModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
