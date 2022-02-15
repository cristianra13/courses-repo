/**
 * Archivo que permite controlar las rutas referentes al usuario
 */

import { Routes } from '@angular/router';
import { UsuarioNuevoComponent } from './usuario-nuevo.component';
import { UsuarioEditarComponent } from './usuario-editar.component';
import { UsuarioDetalleComponent } from './usuario-detalle.component';

export const USUARIO_ROUTES: Routes = [
      { path: 'nuevo', component: UsuarioNuevoComponent },
      { path: 'editar', component: UsuarioEditarComponent },
      { path: 'detalle', component: UsuarioDetalleComponent },
      { path: '**', pathMatch: 'full', redirectTo: 'nuevo' } // esta funciona como ruta predeterminada
 ];
