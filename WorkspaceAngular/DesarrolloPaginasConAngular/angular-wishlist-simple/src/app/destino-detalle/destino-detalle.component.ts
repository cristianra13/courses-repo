import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DestinosApiClient } from '../models/destinos-api-client.model';
import { DestinoViaje } from '../models/destino-viaje.model';

@Component({
  selector: 'app-destino-detalle',
  templateUrl: './destino-detalle.component.html',
  styles: [
  ]
})
export class DestinoDetalleComponent implements OnInit {

  destino: DestinoViaje;

  constructor(private route: ActivatedRoute, private destinosApiClient: DestinosApiClient)
  {

  }

  ngOnInit(): void
  {
    const id = this.route.snapshot.paramMap.get('id');
    this.destino = null;
  }

}
