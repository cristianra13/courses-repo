import { Component } from '@angular/core';
import { DbzService } from '../services/dbz.service';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html'
})
export class CharactersComponent {

  // Recibimos los personajes desde mainPageComponent
  // @Input() characters: Character[] = [];

  constructor(private dbzService: DbzService) {

  }

  get characters() {
    return this.dbzService.characters;
  }

}
