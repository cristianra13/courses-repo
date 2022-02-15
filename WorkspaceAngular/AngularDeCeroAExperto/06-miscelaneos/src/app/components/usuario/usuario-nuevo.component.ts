import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-usuario-nuevo',
  template: `
    <p>
      usuario-nuevo works!
    </p>
  `,
  styles: [
  ]
})
export class UsuarioNuevoComponent implements OnInit {

  constructor(private route: ActivatedRoute)
  {
    // obtenemos el id envíado por el padre
    this.route.parent.params.subscribe(params => {
      console.log('Ruta hija - usuario nuevo');

      console.log(params);
    });

  }

  ngOnInit(): void {
  }

}
