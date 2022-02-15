import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent
{
  nombre = 'Capitan America';
  nombre2 = 'crIstIAn ReAL AriZA';
  arreglo = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  PI = Math.PI;
  porcentaje = 0.234;
  salario = 1234.5;
  fecha = new Date();

  idioma = 'es';
  videoUrl = 'https://www.youtube.com/embed/EXkwYTNfCKg';

  activar = true;

  valorPromesa = new Promise<string>( (resolve) =>
  {
    setTimeout(() =>
    {
      resolve('Llega la data');
    }, 4500);
  });

  heroe = {
    nombre: 'Logan',
    clave: 'Wolverine',
    edad: 500,
    direccion: {
      calle: 'Primera',
      casa: 20
    }
  };
}
