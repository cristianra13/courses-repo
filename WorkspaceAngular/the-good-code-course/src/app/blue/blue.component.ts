import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-blue',
  templateUrl: './blue.component.html',
  styleUrls: ['./blue.component.css']
})
export class BlueComponent implements OnInit, OnDestroy, OnChanges {

  @Input() title: string;
  @Output() clickEvent = new EventEmitter<number>();

  clicks = 0;
  isEven = true;
  /*url = 'https://angular.io/assets/images/logos/angular/angular.svg';
  starClass = 'text-right';*/


  constructor() {
    console.log('%c_[constructor]_ BlueComponent', 'color: blue');
  }

  ngOnInit(): void {
    console.log('%c_[ngOnInit]_', 'color: blue');
  }

  ngOnDestroy(): void {
    console.log('%c_[ngOnDestroy]_ BlueComponent', 'color: blue');
  }

  // Salta cada vez que detecta que algo cambio, si el valor o el resultado es igual, no salta
  ngOnChanges(changes: SimpleChanges) {
    console.log('%c_[ngOnChanges]_ BLueComponent', 'color: blue', changes);
  }

  onClick(event: MouseEvent){
    // console.log(event);
    this.clicks++;
    this.clickEvent.emit(this.clicks);
  }

  /*
   con el signo ?, decimos que el evento es opcional, puede que se reciba o no
   Los opcionales deben ir al final siempre
   */
  sayHi(nombre: string, event?: MouseEvent){
    // console.log(`Hola,${nombre}`);
    if (event){
      // con stopPropagation() evitamos que se ejecuten los demas llamdos a metodos y demas,
      // solo se ejecuta el metodo o funcion que se llama desde donde se declara el metodo sayHi, evitando la ejecucion de los demas
      event.stopPropagation();
    }
  }

}
