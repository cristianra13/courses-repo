import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { DestinoViaje } from '../models/destino-viaje.model';
import { DestinosApiClient } from '../models/destinos-api-client.model';
import { Store } from '@ngrx/store';
import { AppState } from '../app.module';
import { ElegidoFavoritoAction, NuevoDestinoAction } from '../models/destinos-viajes-state.model';

@Component({
  selector: 'app-lista-destinos',
  templateUrl: './lista-destinos.component.html'
})
export class ListaDestinosComponent implements OnInit
{

  destinos: DestinoViaje[] = [];
  @Output() onItemAdded: EventEmitter<DestinoViaje>;
  updates: string[];
  all;

  constructor(public destinosApiClient: DestinosApiClient, private store: Store<AppState>)
  {
    this.onItemAdded = new EventEmitter();
    this.updates = [];
    // nos interesa las actualizaciones sobre favorito
    this.store.select(state => state.destinos.favorito)
      .subscribe(data => {
        const fav = data;
        if (data != null)
        {
          this.updates.push('Se ha elegido a ' + data.nombre);
        }
      });
    this.all = store.select(state => state.destinos.items).subscribe(items => this.all = items);
  }

  ngOnInit(): void {
  }

  agregado(destino: DestinoViaje)
  {
    this.destinosApiClient.add(destino);
    this.onItemAdded.emit(destino);
  }

  elegido(destino: DestinoViaje)
  {
    this.destinosApiClient.elegir(destino);
  }

  getAll()
  {

  }

}
