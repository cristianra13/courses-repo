import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HeroesService } from '../../servicios/heroes.service';

@Component({
  selector: 'app-heroe',
  templateUrl: './heroe.component.html'
})
export class HeroeComponent
{

  heroe: any = {};

  constructor(private activatedRoute: ActivatedRoute,
              private heroesService: HeroesService)
  {
    /**
     * Tomamos parametros que llegan
     * Cabe aclarar que acá tomamos id, porque en el route agregamos heroe/id
     * es ese mismo que tomamos, si agregamos otro nombre, cambiamos el id por el que se agrega
     */
    this.activatedRoute.params.subscribe(params =>
    {
      console.log(params['id']);
      this.heroe = this.heroesService.getHeroe(params['id']);
    });


  }

}
