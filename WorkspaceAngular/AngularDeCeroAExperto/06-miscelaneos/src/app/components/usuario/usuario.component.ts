import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html'
})
export class UsuarioComponent implements OnInit {

  constructor(private route: ActivatedRoute)
  {
    // obtenemos el id envíado por el padre
    this.route.params.subscribe(params => {
      console.log('Ruta padre');

      console.log(params);
    });

  }

  ngOnInit(): void
  {
  }

}
