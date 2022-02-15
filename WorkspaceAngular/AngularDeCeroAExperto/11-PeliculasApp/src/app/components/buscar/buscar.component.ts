import { Component, OnInit } from '@angular/core';
import { PeliculasService } from 'src/app/services/peliculas.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html'
})
export class BuscarComponent implements OnInit
{

  buscar = '';

  constructor(public _ps: PeliculasService, public route: ActivatedRoute)
  {
    this.route.params.subscribe(params => {
      console.log(params);
      if (params['texto'])
      {
        this.buscar = params['texto'];
        this.buscarPelicula();
      }
    });
  }

  ngOnInit(): void {
  }

  buscarPelicula()
  {
    if (this.buscar.length === 0)
    {
      return;
    }

    this._ps.searchMovie(this.buscar).subscribe( );

  }

}
