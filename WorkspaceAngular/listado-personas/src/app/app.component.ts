import {Component, OnInit} from '@angular/core';
import {Persona} from './persona.model';
import {LogginServiceService} from './LogginService.service';
import {PersonasService} from './personas.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  titulo = 'listado de personas';
  personas: Persona[] = [];

  constructor(private logginService: LogginServiceService, private personasService: PersonasService) {
  }

  // este metodo se ejecuta despues del constructor
  ngOnInit(): void {
    // inicializamos el arreglo de personas
    this.personas = this.personasService.personas;
  }



  /*// el evento que se emite desde el formulario html, es un evento persona
  onPersonaAgregada(persona: Persona) {
    // this.logginService.enviaMensajeAConsola('Agregamos perosna al arreglo '+ persona.nombre);
    // this.personas.push(persona); // el objeto persona se emitio desde el componente hijo
    this.personasService.onAgregarPersona(persona);
  }*/
}
