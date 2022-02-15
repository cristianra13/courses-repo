import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  title = 'Aplicación de calculadora';
  resultadoPadre: number;

  constructor() {
  }

  onResultado(resultado: number) {
    // se recibe el resultado que llega desde el componente hijo
    this.resultadoPadre = resultado;
  }

}
