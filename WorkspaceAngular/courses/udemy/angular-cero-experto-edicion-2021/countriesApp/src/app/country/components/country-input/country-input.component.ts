import { Component, EventEmitter, Output, OnInit, Input } from '@angular/core';
import { Subject, debounceTime } from 'rxjs';

@Component({
  selector: 'app-country-input',
  templateUrl: './country-input.component.html',
  styles: [
  ]
})
export class CountryInputComponent implements OnInit {

  @Input() placeholder: string = '';
  // Enviaos el termino de búsqueda
  @Output() onEnter: EventEmitter<string> = new EventEmitter();
  // Se emite cuando la persona deja de escribir
  @Output() onDebounce: EventEmitter<string> = new EventEmitter();
  debouncer: Subject<string> = new Subject;
  searchTerm: string = '';

  ngOnInit() {
    this.debouncer
    .pipe(debounceTime(300)) // Se emite el debounce cuando se deja de escribir por 300 milisegundos
    .subscribe( value => {
      this.onDebounce.emit(value);
    });
  }

  search() {
    this.onEnter.emit(this.searchTerm);
  }

  keyPress() {
    this.debouncer.next(this.searchTerm);
  }

}
