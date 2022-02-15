import { Component, OnInit } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { map } from 'rxjs/operators';
import { Game } from '../../interfaces/interfaces';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  juegos: any[] = [];

  constructor(private db: AngularFirestore) { }

  ngOnInit(): void
  {
    /**
     * valueChanges() Al detectar un cambio se dispara
     */
    this.db.collection('goty').valueChanges().
    pipe(
      // transformamos las respuesta
      map( (resp: Game[]) => resp.map( ({nombre, votos}) => ({name: nombre, value: votos}) ) )

        /* return resp.map(juego => {
          return {
            name: juego.nombre,
            value: juego.votos
          }
        }); */

    ).subscribe( juegos => {
      this.juegos = juegos;
    });
  }

}
