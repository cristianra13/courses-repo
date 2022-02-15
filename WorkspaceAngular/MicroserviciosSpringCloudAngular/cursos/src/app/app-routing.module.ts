import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { ExamenesComponent } from './components/examenes/examenes.component';
import { AlumnosFormComponent } from './components/alumnos/alumnos-form/alumnos-form.component';
import { CursoFormComponent } from './components/cursos/curso-form/curso-form.component';
import { ExamenesFormComponent } from './components/examenes/examenes-form/examenes-form.component';
import { AsignarAlumnosComponent } from './components/cursos/asignar-alumnos/asignar-alumnos.component';
import { AsignarExamenesComponent } from './components/cursos/asignar-examenes/asignar-examenes.component';
import { ResponderExamenComponent } from './components/alumnos/responder-examen/responder-examen.component';

const routes: Routes = [
  {path: 'alumnos', component: AlumnosComponent},
  {path: 'alumnos/form', component: AlumnosFormComponent},
  {path: 'alumnos/form/:id', component: AlumnosFormComponent},
  {path: 'alumnos/responder-examen/:id', component: ResponderExamenComponent},
  {path: 'cursos', component: CursosComponent},
  {path: 'cursos/form', component: CursoFormComponent},
  {path: 'cursos/form/:id', component: CursoFormComponent},
  {path: 'cursos/asignar-alumnos/:id', component: AsignarAlumnosComponent},
  {path: 'cursos/asignar-examenes/:id', component: AsignarExamenesComponent},
  {path: 'examenes', component: ExamenesComponent},
  {path: 'examenes/form', component: ExamenesFormComponent},
  {path: 'examenes/form/:id', component: ExamenesFormComponent},
  {path: '**', pathMatch: 'full', redirectTo: 'alumnos'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
