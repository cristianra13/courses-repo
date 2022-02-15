import { Component, Input } from '@angular/core';
import { Character } from '../interfaces/dbz.interface';
import { DbzService } from '../services/dbz.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html'
})
export class AddComponent {

  @Input() new: Character = {
    name: 'Trunks',
    power: 14000
  }

  // Output emite eventos, en este caso hacía el padre (mainPage) quien lo recibe
  // @Output() onNewCharacter: EventEmitter<Character> = new EventEmitter();

  constructor(private dbzService: DbzService) {

  }

  add() {
    if (this.new.name.trim().length === 0) {
      return;
    }
    // enviamos personaje al componente padre -> mainPage
    // this.onNewCharacter.emit(this.new);
    this.dbzService.addCharacter(this.new);
    this.new = {name: '', power: 0};
  }

}
