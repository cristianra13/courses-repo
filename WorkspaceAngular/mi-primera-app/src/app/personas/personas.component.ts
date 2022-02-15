/*
Un componente es una clase de TypeScript
Se agrega export porque se va a usar en otros lugares
 */
import {Component} from '@angular/core';

@Component({
  /*este es el nombre que se va a usar en HTML, debe ser un nombre unico*/
  selector: 'app-personas',
  /*Plantilla HTML con la cual se va a trabajar, se debe especificar la url donde se encuentra la plantilla HTML*/
  templateUrl: './personas.component.html',
  styleUrls: ['personas.component.css']
  /*
  * Se puede agregar el html a template, este debe ser en una sola linea
  * Para agregar varias se debe usar el simbolo de backtig -> ``
  * Esto sirve tambien para CSS
  * */
  /*template: `<h1>Listado de personas</h1>
            <app-persona></app-persona>
            <app-persona></app-persona>`*/
})
/*Se debe registrar el component en el archivo app.module.ts*/
export class PersonasComponent{
  agregarPersona = true;
  agregarPersonaStatus = 'No se ha agregado ninguna persona';
  tituloPersona = 'Ingeniero';
  personaCreada: boolean;

  constructor() {
    setTimeout(() => {
      this.agregarPersona = false;
    }, 3000);
  }

  onAgregarPersona() {
    this.personaCreada = true;
    this.agregarPersonaStatus = 'Persona agregada';
  }

  onModificarPersona(event: Event) {
   // this.tituloPersona = ( <HTMLInputElement> event.target).value;
    // Indicamos que el target es de tipo HTML y con la propiedad .value, accedemos a cada uno de los valores de la caja de texto
    this.tituloPersona = ( event.target as HTMLInputElement).value;
  }

}
