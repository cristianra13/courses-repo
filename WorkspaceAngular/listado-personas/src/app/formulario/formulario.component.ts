import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Persona} from '../persona.model';
import {LogginServiceService} from '../LogginService.service';
import {PersonasService} from '../personas.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {
  // Le decimos a la clase padre que vamos a enviar información
  // el objeto emitira un evento
  // adicional debemos agregarlo como proveedor providers: [LogginServiceService], ya que angular lo exige
  /**
   * U otra acción que podemos hacer es definirlo en el archivo app.module.ts en la parte de providers
   * para que este sea usado en varias partes pero solo con una instancia creada
    */

  // @Output() personaCreada = new EventEmitter<Persona>();

  nombreInput: string;
  apellidoInput: string;

  // pasamos el nombre de la referencia local
  // @ViewChild('nombreInput') nombreInput: ElementRef;
  // @ViewChild('apellidoInput') apellidoInput: ElementRef;

  // Vamos a injectar el servicio de LogginService a traves del constructor con Dependency Onjection
  // Automaticamente, angular va a crear una instancia de logginservice
  constructor(private logginService: LogginServiceService, private personasService: PersonasService) {
    // A traves del service personasService, nos suscribimos a la emisión del mensaje, recibimos el indice y mandamos una alerta
    this.personasService.saludar.subscribe(
      (indice: number) => alert('El indice es: ' + indice)
    );
  }

  ngOnInit(): void {
  }

  // las variables nombreInput y apellidoInput son de tipo HTML Element
  // onAgregarPersona(nombreInput: HTMLInputElement, apellidoInput: HTMLInputElement)
  // Vamos a usar ViewChild
  onAgregarPersona()
  {
    // Accedemos al valor del ElementRef con nativeElement.ref
    // let persona1 = new Persona(this.nombreInput.nativeElement.value, this.apellidoInput.nativeElement.value);

    let persona1 = new Persona(this.nombreInput  , this.apellidoInput);
    // Lammamos el metodo del servicio
    // this.logginService.enviaMensajeAConsola('Enviamos persona: ' + persona1.nombre + ' ' + persona1.apellido);

    // this.personas.push(persona1);
    // estamos emitiendo el objeto persona1
    // persona1 es el objeto que se envía a la clase padre

    // Ya no es necesario emitir este evento ya que estamos trabajando directamente con el servicio
    // this.personaCreada.emit(persona1);

    // Usamos el servicio
    this.personasService.onAgregarPersona(persona1);
  }
}
