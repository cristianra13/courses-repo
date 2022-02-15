import { DestinoViaje } from './destino-viaje.model';
import { Subject, BehaviorSubject} from 'rxjs';
import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from '../app.module';
import { NuevoDestinoAction, ElegidoFavoritoAction } from './destinos-viajes-state.model';

@Injectable()
export class DestinosApiClient
{
    constructor(private store: Store<AppState>)
    {
    }

     add(d: DestinoViaje)
     {
       this.store.dispatch(new NuevoDestinoAction(d));
     }

     elegir(d: DestinoViaje)
     {
      this.store.dispatch(new ElegidoFavoritoAction(d));
     }
}
