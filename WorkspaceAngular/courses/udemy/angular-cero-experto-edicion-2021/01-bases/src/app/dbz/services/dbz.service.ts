import { Injectable } from "@angular/core";
import { Character } from "../interfaces/dbz.interface";

@Injectable()
export class DbzService {

  private _characters: Character[] = [
    {
      name: 'Goku',
      power: 25000
    },
    {
      name: 'Vegeta',
      power: 14000
    }
  ];

  constructor() {}

  get characters(): Character[] {
    // rompemos el paso por referencia de javascript
    return [...this._characters];
  }

  addCharacter(character: Character) {
    this._characters.push(character);
  }

}
