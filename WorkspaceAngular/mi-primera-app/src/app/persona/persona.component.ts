import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent{

  /*Si se inicializa la variable, se puede eliminar el tipo de dato*/
  nommbrePersona = 'Cristian';
  apellidoPersona = 'Real';
  edad = 27;

  constructor() { }

}
