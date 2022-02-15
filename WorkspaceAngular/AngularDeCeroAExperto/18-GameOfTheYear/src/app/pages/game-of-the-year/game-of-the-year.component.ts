import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/interfaces/interfaces';
import { GameService } from '../../services/game.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-game-of-the-year',
  templateUrl: './game-of-the-year.component.html',
  styleUrls: ['./game-of-the-year.component.css']
})
export class GameOfTheYearComponent implements OnInit {

  juegos: Game[] = [];

  constructor(private service: GameService) { }

  ngOnInit(): void
  {
    this.service.getNominados().subscribe(juegos => {
       this.juegos = juegos;
    });
  }

  votar(juego: Game)
  {
    this.service.votarJuego(juego.id).subscribe( (resp: {ok: boolean, mensaje: string}) => {
      if (resp.ok)
      {
        Swal.fire('Gracias', resp.mensaje, 'success');
      }
      else
      {
        Swal.fire('Oops', resp.mensaje, 'error');
      }
    });
  }

}
