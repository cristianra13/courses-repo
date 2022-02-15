import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuariosRoutingModule } from './usuarios-routing.module';
import { UsuarioComponent } from './pages/usuario/usuario.component';
import { AgregarComponent } from './pages/agregar/agregar.component';
import { EditarComponent } from './pages/editar/editar.component';
import { ListadoComponent } from './pages/listado/listado.component';


@NgModule({
  declarations: [
    UsuarioComponent,
    AgregarComponent,
    EditarComponent,
    ListadoComponent
  ],
  imports: [
    CommonModule,
    UsuariosRoutingModule
  ]
})
export class UsuariosModule { }
