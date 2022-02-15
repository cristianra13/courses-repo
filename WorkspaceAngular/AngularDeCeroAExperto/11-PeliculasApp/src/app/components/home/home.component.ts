import { Component, OnInit } from '@angular/core';
import { PeliculasService } from '../../services/peliculas.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit
{
  cartelera: any;
  populares: any;
  proximamente: any;

  constructor(public peliculasService: PeliculasService)
  {
    this.peliculasService.getCartelera().subscribe( data => this.cartelera = data );

    this.peliculasService.getPopular().subscribe( data => this.populares = data );

    this.peliculasService.getUpcoming().subscribe( data => this.proximamente = data );
  }

  ngOnInit(): void {
  }

}
