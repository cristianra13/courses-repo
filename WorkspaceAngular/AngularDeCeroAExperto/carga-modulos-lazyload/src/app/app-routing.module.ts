import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

/*
  Rutas principales de la aplicacion
 */
const routes: Routes = [
  {
    // cuando se ingrese a la ruta auth, va a cargar de forma perezosa las rutas hijas
    path: 'auth',
    // cargamos el modulo completo, ya que en este se encuentra todo lo del modulo, rutas, pipes, etc
    loadChildren: () => import('./auth/auth.module').then(modul => modul.AuthModule)
  },
  {
    path: 'producto',
    loadChildren: () => import('./productos/productos.module').then(module => module.ProductosModule)
  },
  {
    path: 'usuario',
    loadChildren: () => import('./usuarios/usuarios.module').then(module => module.UsuariosModule)
  },
  {
    // si ingresa al path vacío o cualquier otra ruta no definida, redirige a auth
    path: '**', redirectTo: 'auth'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
