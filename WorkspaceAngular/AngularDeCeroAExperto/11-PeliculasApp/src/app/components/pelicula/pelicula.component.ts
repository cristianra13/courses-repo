import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PeliculasService } from 'src/app/services/peliculas.service';

@Component({
  selector: 'app-pelicula',
  templateUrl: './pelicula.component.html',
  styles: [
  ]
})
export class PeliculaComponent implements OnInit
{
  pelicula: any;
  regresarA = '';
  busqueda = '';

  constructor(public _ps: PeliculasService, public route: ActivatedRoute)
  {
    this.route.params.subscribe(parametros => {
      this.regresarA = parametros.pagina;

      if (parametros.busqueda)
      {
        this.busqueda = parametros.busqueda;
      }

      this._ps.getpelicula(parametros.id).subscribe( pelicula => this.pelicula = pelicula );
    });
  }

  ngOnInit(): void {
  }

}
