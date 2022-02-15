import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { RatingModule } from 'ng-starrating';

import { SlideShowComponent } from './slide-show/slide-show.component';
import { PeliculasPosterGridComponent } from './peliculas-poster-grid/peliculas-poster-grid.component';
import { PipesModule } from '../pipes/pipes.module';
import { CastSlideShowComponent } from './cast-slide-show/cast-slide-show.component';



@NgModule({
  declarations: [
    NavbarComponent,
    SlideShowComponent,
    PeliculasPosterGridComponent,
    CastSlideShowComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    RatingModule,
    PipesModule
  ],
  exports: [
    // se exporta para usar fuera de este modulo
    NavbarComponent,
    SlideShowComponent,
    PeliculasPosterGridComponent,
    CastSlideShowComponent
  ]
})
export class ComponentsModule { }
