import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {Persona} from '../persona.model';
import {PersonasService} from '../personas.service';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {

// Le decimos que vamos recibir información de la clase padre a la clase hija
  // por lo cual ya podemos usar la variable persona en persona.component.html
  @Input() persona: Persona;
  @Input() indice; number;

  constructor(private personasService: PersonasService) { }

  ngOnInit(): void {
  }

  // el emisor, al dar clic sobre el boton editar se emite el indice que se ha seleccionado
  emitirSaludo(){
    this.personasService.saludar.emit(this.indice);
  }
}
