import { Component } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html'
})
export class BodyComponent
{
  mostrar = true;

  frase: any = {
    mensaje: 'Un gran poder requiere una gran responsabilidad',
    autor: 'Ben Parker'
  };

  // ngFor trabaja en base a arreglos
  personajes: string[] = ['Spiderman', 'iron man', 'Thor'];


}
