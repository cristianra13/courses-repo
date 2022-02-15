import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tarjetas',
  templateUrl: './tarjetas.component.html'
})
export class TarjetasComponent implements OnInit
{

  @Input() items: any[] = [];

  constructor(private router: Router)
  {

  }

  ngOnInit(): void
  {
  }

  verArtista(item: any): void
  {
    let artistaId;

    if (item.type === 'artist')
    {
      artistaId = item.id;
    }
    else
    {
      artistaId = item.artists[0].id;
    }
    // Como la ruta tiene un parametro, agregamos las llaves, en este caso el parametro es el id
    this.router.navigate([ '/artist', artistaId ]);
  }

}
