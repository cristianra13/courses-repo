
import { Subject, BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import {DestinoViajeModel} from '../models/destino-viaje.model';

@Injectable({
  providedIn: 'root'
})
export class DestinosAPiClient {
  destinos: DestinoViajeModel[];
  public nombre: string;
  public url: string;
  current: Subject<DestinoViajeModel> = new BehaviorSubject<DestinoViajeModel>(null);

  constructor(){
    this.destinos = [];
    this.nombre = '';
    this.url = '';
  }

  add(d: DestinoViajeModel){
    this.destinos.push(d);
  }

  getAll(): DestinoViajeModel[]{
    return this.destinos;
  }

  getById(id: string): DestinoViajeModel{
    return this.destinos.filter( function (d){return d.id.toString() === id; })[0];
  }

  elegir(d: DestinoViajeModel){
    this.destinos.forEach(x => x.setSelected(false));
    d.setSelected(true);
    this.current.next(d);
  }

  // metodo para que los demas metodos se registren
  subscribedOnChange(fn){
    this.current.subscribe(fn);
  }
}
