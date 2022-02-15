import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { AgregarComponent } from './pages/agregar/agregar.component';
import { EditarComponent } from './pages/editar/editar.component';
import { ListadoComponent } from './pages/listado/listado.component';
import { UsuarioComponent } from './pages/usuario/usuario.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {path: 'usuario', component: UsuarioComponent},
      {path: 'agregar', component: AgregarComponent},
      {path: 'editar', component: EditarComponent},
      {path: 'listado', component: ListadoComponent},
      {path: '**', redirectTo: 'listado'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuariosRoutingModule { }
