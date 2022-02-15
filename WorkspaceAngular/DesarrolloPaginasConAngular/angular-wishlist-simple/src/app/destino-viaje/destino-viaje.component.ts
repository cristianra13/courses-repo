import { Component, OnInit, Input, HostBinding, EventEmitter, Output } from '@angular/core';
import { DestinoViaje } from '../models/destino-viaje.model';
import { Store } from '@ngrx/store';
import { AppState } from '../app.module';
import { VoteUpAction, VoteDownAction } from '../models/destinos-viajes-state.model';

@Component({
  selector: 'app-destino-viaje',
  templateUrl: './destino-viaje.component.html',
  styleUrls: ['./destino-viaje.component.css']
})
export class DestinoViajeComponent implements OnInit
{

  // llega un array destino del padre que es lista-destinos.component
  @Input() destino: DestinoViaje;
  @Input('index') posicion: number;
  @HostBinding('attr.class') cssClass = 'col-md-4 mb-4';
  @Output() clicked: EventEmitter<DestinoViaje>;

  constructor(private store: Store<AppState>)
  {
    this.clicked = new EventEmitter();
  }

  ngOnInit(): void {
  }

  ir()
  {
    this.clicked.emit();
    return false;
  }

  voteUp()
  {
    this.store.dispatch(new VoteUpAction(this.destino));
    return false;
  }

  voteDown()
  {
    this.store.dispatch(new VoteDownAction(this.destino));
    return false;
  }
}
