import { Component } from '@angular/core';
import { PeliculasService } from './services/peliculas.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent
{

  constructor(public peliculaService: PeliculasService)
  {
    //this.peliculaService.getPopular().subscribe(resp => console.log(resp));
    //this.peliculaService.searchMovie('spiderman').subscribe(resp => console.log(resp));
  }
}
