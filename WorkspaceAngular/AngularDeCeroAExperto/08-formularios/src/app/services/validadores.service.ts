import { Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

interface errorValidate {
  [s: string]: boolean;

}


@Injectable({
  providedIn: 'root'
})
export class ValidadoresService
{

  constructor() { }

  existeUsuario(control: FormControl): Promise<errorValidate> | Observable<errorValidate>
  {
    // en el caso de que no haya ningun valor en el campo, resuelve null
    if (!control.value)
    {
      // promesa resulta inmediatamente
      return Promise.resolve(null);
    }

    // definimos la promesa
    return new Promise( (resolve, reject) => {
      setTimeout( () => {
        // Podemos disparar una peticion http para validar el usuario en un backend
        if (control.value === 'strider') // Si el usuario escribe strider quiere decir que ya existe el usuario
        {
          resolve({ existe: true });

        }
        else
        {
          resolve( null );
        }
      }, 3500);
    } );
  }

  /**
   *
   * @param control { [s: string]: boolean } voy a retornar un objeto que tiene una propiedad s de tipo string,
   * y esta retorna un boolean
   */
  // noApellido(control: FormControl): { [s: string]: boolean }
  noApellido(control: FormControl): errorValidate
  {
    if (control.value?.toLowerCase() === 'real a' )
    {
      return {
        noApellido: true
      }
    }
    return null;
  }

  /**
   *
   * @param password Le establecemos el error al formulario
   * @param passwordConfirm
   */
  passwordsIguales( password: string, passwordConfirm: string )
  {
    // recibimos un formulario ya que la validacion se hace a nivel de formulario
    return ( formGroup: FormGroup ) => {
      const passControl = formGroup.controls[password];
      const passConfirm = formGroup.controls[passwordConfirm];

      if (passControl.value === passConfirm.value)
      {
        passConfirm.setErrors(null);
      }
      else
      {
        passConfirm.setErrors( { noEsIgual: true });
      }
    }
  }
}
