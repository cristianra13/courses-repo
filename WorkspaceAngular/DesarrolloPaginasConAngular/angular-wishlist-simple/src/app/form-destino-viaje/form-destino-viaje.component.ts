import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { DestinoViaje } from '../models/destino-viaje.model';
import { FormGroup, FormBuilder, Validators, FormControl, ValidatorFn } from '@angular/forms';
import { fromEvent } from 'rxjs';
import { map, filter, debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ajax, AjaxResponse } from 'rxjs/ajax';

@Component({
  selector: 'app-form-destino-viaje',
  templateUrl: './form-destino-viaje.component.html',
  styles: [
  ]
})
export class FormDestinoViajeComponent implements OnInit
{

  @Output() onItemAdded: EventEmitter<DestinoViaje>;
  fg: FormGroup;
  minLongNombre = 3;
  searchResults: string[];



  constructor(fb: FormBuilder)
  {
    this.onItemAdded = new EventEmitter();
    this.fg = fb.group({
      nombre: ['', Validators.compose([
        this.nombreValidatorParametrizable(this.minLongNombre) // agregamos nuestro validador
      ])],
      url: ['']
    });

    // registrar un observable
    this.fg.valueChanges.subscribe((form: any) => {
      console.log(form);
    });
  }

  ngOnInit(): void
  {
    let elemNombre = <HTMLInputElement> document.getElementById('nombre');
    // Escucha cada vez que se ingresa una tecla en la caja de texto
    fromEvent(elemNombre, 'input') // esto genera un observable de eventos de entrada
    .pipe(
      map( (x: KeyboardEvent) => (x.target as HTMLInputElement).value  ) , // tomamos el valor de la tecla
      filter(text => text.length > 2), // si la cadena es mayor a dos caracteres, continua
      debounceTime(200), // se queda en stop 200 milisegundos para traer la cadena
      distinctUntilChanged(), // Si van llegando distintos valores al operador anterior hasta que llega algo distinto
      switchMap(() => ajax("/assets/datos.json")) // desde acá podriamos llamar un servicio de angular que a su vez llamaria un servicio de un backend
      ).subscribe(ajaxResponse => {
        console.log(ajaxResponse.response);

        this.searchResults = ajaxResponse.response;
      }) ;
  }

  guardar(nombre: string, url: string): boolean
  {
    const dest = new DestinoViaje(nombre, url);
    this.onItemAdded.emit(dest);
    return false;
  }

  // validadores
  nombreValidator(control: FormControl): {[s: string]: boolean}
  {
    const longitud = control.value.toString().trim().length;
    if (longitud > 0 && longitud < 5)
    {
      return {invalidNombre: true};
    }
    return null;
  }

  // validador parametrizable

  nombreValidatorParametrizable(minLong: number): ValidatorFn
  {
    return (control: FormControl): { [s: string]: boolean } | null => {
      const longitud = control.value.toString().trim().length;
      if (longitud > 0 && longitud < minLong)
      {
        return {minLongNombre: true};
      }
      return null;
    }
  }

}
