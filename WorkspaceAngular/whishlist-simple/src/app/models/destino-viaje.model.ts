import {v4 as uuid} from 'uuid';

export class DestinoViajeModel{

  private selected: boolean;

  public servicios: string[];

  id = uuid();

  constructor(public nombre: string, public url: string) {
    this.servicios = ['Pileta', 'Desayuno'];
  }

  isSelected(): boolean{
    return this.selected;
  }

  setSelected(isSelected: boolean){
    this.selected = true;
  }

}
