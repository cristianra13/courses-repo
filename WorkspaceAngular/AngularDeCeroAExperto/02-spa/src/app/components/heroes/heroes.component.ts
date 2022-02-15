import { Component, OnInit } from '@angular/core';
import { HeroesService, Heroe } from '../../servicios/heroes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html'
})
export class HeroesComponent implements OnInit {

  heroes: Heroe[] = [];

  /**
   * Debemos inicializar el servicio en el constructor
   * @param heroesService
   */
  constructor(private heroesService: HeroesService,
              private router: Router)
  {
    //console.log('Constructor heroes');

  }

  ngOnInit(): void
  {
    this.heroes = this.heroesService.getheroes();
    //console.log(this.heroes);
  }

  verHeroe(idx: number)
  {
    this.router.navigate(['/heroe', idx]);
  }

}
