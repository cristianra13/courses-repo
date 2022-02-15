import {Persona} from './persona.model';
import {LogginServiceService} from './LogginService.service';
import {EventEmitter, Injectable} from '@angular/core';

// Debemos decirle que vamos a injectar un servicio dentro de otro servicio
// en este caso, logginService dentro de personasService
// siempre se deve especificar en la clase donde se va a injectar el otro servicio, en este caso personasService
@Injectable()
export class PersonasService
{
  personas: Persona[] = [new Persona('Cristian', 'Real'), new Persona('Maria', 'Juarez')];
  saludar = new EventEmitter<number>();


  constructor(private logginService: LogginServiceService) {
  }

  // el evento que se emite desde el formulario html, es un evento persona
  onAgregarPersona(persona: Persona)
  {
    this.logginService.enviaMensajeAConsola('se agrego a ' + persona.nombre + ' ' +  persona.apellido + ' dentro del listado');
    this.personas.push(persona);
  }
}
