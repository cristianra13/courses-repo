import {v4 as uuid} from 'uuid';

export class DestinoViaje
{
  nombre: string;
  imagenUrl: string;
  private selected: boolean;
  servicios: string[];
  public votes = 0;
  id = uuid();

  constructor(nombre: string, imagenUrl: string)
  {
    this.nombre = nombre;
    this.imagenUrl = imagenUrl;
    this.servicios = ['Pileta', 'Desayuno'];
  }

  isSelected(): boolean
  {
    return this.selected;
  }

  setSelected(isSelected: boolean)
  {
    this.selected = isSelected;
  }

  voteUp(): any
  {
    this.votes++;
  }
  voteDown(): any
  {
    this.votes--;
  }
}
