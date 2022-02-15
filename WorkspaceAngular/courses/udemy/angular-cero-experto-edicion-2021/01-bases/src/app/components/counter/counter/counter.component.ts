import { Component } from '@angular/core';

@Component({
  selector: 'app-counter',
  template: `

    <h1>{{ title }}</h1>
    <h3>The base is: <strong>{{ numberBase }}</strong></h3>

    <button (click)=" accumulate(numberBase) "> + {{ numberBase }} </button>

    <span>{{ numberCount}}</span>

    <button (click)=" accumulate(-numberBase) "> - {{ numberBase }} </button>

  `
})
export class CounterComponent {

  title: string = 'Contador App';
  numberCount: number = 0;
  numberBase: number = 5;


  accumulate(value: number) {
    this.numberCount += value;
  }
}
