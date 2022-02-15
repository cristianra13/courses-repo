import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InicioComponent } from './pages/inicio/inicio.component';
import { GameOfTheYearComponent } from './pages/game-of-the-year/game-of-the-year.component';


const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'goty', component: GameOfTheYearComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'inicio' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
