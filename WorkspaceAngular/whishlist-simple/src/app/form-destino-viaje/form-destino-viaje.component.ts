import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DestinoViajeModel} from '../models/destino-viaje.model';
import {FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {fromEvent} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map, switchMap} from 'rxjs/operators';
import {ajax} from 'rxjs/ajax';

@Component({
  selector: 'app-form-destino-viaje',
  templateUrl: './form-destino-viaje.component.html',
  styleUrls: ['./form-destino-viaje.component.css']
})
export class FormDestinoViajeComponent implements OnInit {

  @Output() onItemAdded: EventEmitter<DestinoViajeModel>;
  fg: FormGroup;
  minLongitud = 8;
  searchResults: string[];

  constructor(formsBuilder: FormBuilder ) {
    this.onItemAdded = new EventEmitter();
    this.fg = formsBuilder.group({
      nombre: ['', Validators.compose([
        Validators.required,
        this.nombreValidator,
        this.nombreValidadorParametrizable(this.minLongitud)
      ])],
      url: ['']
    });

    // Vamos a registrar un observable
    this.fg.valueChanges.subscribe((form: any) => {
      console.log('Cambio en el formulario: ' + form);
    });
  }

  ngOnInit(): void {
    // Detectar a medida que se va esribiendo en el input, buscar coincidencias
    const elemNombre = document.getElementById('nombre') as HTMLInputElement;
    // esta escuchando cada inserción de teclas en al caja de texto
    // esto genera un observabe de eventos de entrada
    fromEvent(elemNombre, 'input').pipe(
      map((e: KeyboardEvent) => (e.target as HTMLInputElement).value),
      filter(text => text.length > 2), // Si lo que llega en el text cumple la condición, continua
      debounceTime(200), // espera dos decimas de segundo para saber si siguen tecleando
      /*
         si van llegando distintos valores del operador anterior, como si se escribiera algo y se borrara,
         si sigue llegando lo mismo no avanza hasta que se ingrese algo diferente
       */
      distinctUntilChanged(),
      // vamos a consultar un archivo estatico, pero podría ser info que llega de un servicio rest, por ejemplo
      switchMap(() => ajax('/assets/datos.json'))
    ).subscribe(ajaxResponse => {
      console.log(ajaxResponse);
      console.log(ajaxResponse.response);
      this.searchResults = ajaxResponse.response;
    });
  }

  guardar(nombre: string, url: string): boolean{
    const destino = new DestinoViajeModel(nombre, url);
    this.onItemAdded.emit(destino);
    return false;
  }

  nombreValidator(control: FormControl): {
    // retorno de los validadores, siempre es un objeto que tiene una key -> [s: string] y un valor booleano
    [s: string]: boolean;
  } {
    const longitud = control.value.toString().trim().length;
    if (longitud > 0 && longitud < 8){
      return {invalidNombre: true };
    }
    return null;
  }

  // validador parametrizable
  nombreValidadorParametrizable(minLong: number): ValidatorFn {
    return (control: FormControl): {[s: string]: boolean} | null => {
      const longitud = control.value.toString().trim().length;
      if (longitud > 0 && longitud < 8){
        return {minLongNombre: true };
      }
      return null;
    }
  }
}


