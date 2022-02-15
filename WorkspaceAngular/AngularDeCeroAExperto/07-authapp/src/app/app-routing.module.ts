import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { PreciosComponent } from './components/precios/precios.component';
import { ProtegidaComponent } from './components/protegida/protegida.component';
import { CallbackComponent } from './components/callback/callback.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'precios', component: PreciosComponent },
  { path: 'protegida', component: ProtegidaComponent },
  {
    path: 'callback',
    component: CallbackComponent,
    canActivate: [ AuthGuard ] // antes de entrar a la pagina, debe pasar por el AuthGuard
  },
  { path: '**', pathMatch: 'full', redirectTo: 'home' } // ruta por defecto
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule{}
