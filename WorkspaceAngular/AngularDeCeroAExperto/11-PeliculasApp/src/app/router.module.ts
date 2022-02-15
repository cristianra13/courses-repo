import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PeliculaComponent } from './components/pelicula/pelicula.component';
import { BuscarComponent } from './components/buscar/buscar.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'pelicula/:id/:pagina', component: PeliculaComponent },
  { path: 'pelicula/:id/:pagina/:busqueda', component: PeliculaComponent },
  { path: 'buscar', component: BuscarComponent },
  { path: 'buscar/:texto', component: BuscarComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'home' }
];

export const APP_ROUTING = RouterModule.forRoot(routes);
