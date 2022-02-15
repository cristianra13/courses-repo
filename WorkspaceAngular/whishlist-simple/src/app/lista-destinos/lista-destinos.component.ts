import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DestinoViajeModel} from '../models/destino-viaje.model';
import {DestinosAPiClient} from '../reservas/DestinoApiClient';

@Component({
  selector: 'app-lista-destinos',
  templateUrl: './lista-destinos.component.html',
  styleUrls: ['./lista-destinos.component.css']
})
export class ListaDestinosComponent implements OnInit {

  destinos: DestinoViajeModel[];

  @Output() onItemAdded: EventEmitter<DestinoViajeModel>;
  updates: string[];

  constructor(private destinosApiClient: DestinosAPiClient) {
    this.onItemAdded = new EventEmitter();
    this.updates = [];
    this.destinosApiClient.subscribedOnChange((d: DestinoViajeModel) => {
      if (d != null){
        this.updates.push('Se ha elegido a ' + d.nombre);
      }
    });
  }

  ngOnInit(): void {
  }

  agregado(destinoViaje: DestinoViajeModel){
    this.destinosApiClient.add(destinoViaje);
    this.onItemAdded.emit(destinoViaje);
  }

  elegido(destino: DestinoViajeModel){

    this.destinosApiClient.elegir(destino);
    /*this.destinos.forEach(function (x) {x.setSelected(false); });
    destino.setSelected(true);*/

    /*this.destinosApiClient.getAll().forEach(x => x.setSelected(false));
    destino.setSelected(true);*/
  }

}
