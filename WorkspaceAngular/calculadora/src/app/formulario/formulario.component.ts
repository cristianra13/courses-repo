import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit
{
  // Vamos a mandar el resultado a la clase padre
  @Output() resultadoSuma = new EventEmitter<number>();
  primerNumero: number;
  segundoNumero: number;

  constructor() { }

  ngOnInit(): void {
  }

  onSumar(): void{
    let resultado = this.primerNumero + this.segundoNumero;
    this.resultadoSuma.emit(resultado);
  }

}
