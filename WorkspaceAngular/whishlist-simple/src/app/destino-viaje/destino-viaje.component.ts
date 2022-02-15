import {Component, OnInit, Input, HostBinding, EventEmitter, Output} from '@angular/core';
import {DestinoViajeModel} from '../models/destino-viaje.model';

@Component({
  selector: 'app-destino-viaje',
  templateUrl: './destino-viaje.component.html',
  styleUrls: ['./destino-viaje.component.css']
})
export class DestinoViajeComponent implements OnInit {

  // Vamos a recibir el valor por argumento
  @Input() destino: DestinoViajeModel;
  @Input() position: number;

  /*
    @HostBinding() tiene una vinculación directa string a un atributo,
    Cuando el componente DestinoViajeComponent se renderee, con el HTML destino-viaje.component.HTML
    y ese HTML sea envuelto por un tag que genera angular automaticamente, a ese tag que crea angular, en el atributo
    class del css, se le va a asignar col-md-4
   */
  @HostBinding('attr.class') cssClass = 'col-md-4';

  // es un valor de salida que sale hacia el padre
  @Output() clicked: EventEmitter<DestinoViajeModel>;


  constructor() {
    this.clicked = new EventEmitter();
  }

  ngOnInit(): void {
  }

  // este metodo se activa cuando se hace clic sobre el botón del HTML, este metodo emite un evento al componente padre
  ir(){
    this.clicked.emit(this.destino);
    return false;
  }

}
