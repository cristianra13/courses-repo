import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ValidadoresService } from '../../services/validadores.service';

@Component({
  selector: 'app-reactive',
  templateUrl: './reactive.component.html',
  styleUrls: ['./reactive.component.css']
})
export class ReactiveComponent implements OnInit {

  // Referencia al formulario HTML
  forma: FormGroup;


  /**
   *
   * @param formbuilder Nos ayuda a crear formularios mas rapido
   */
  constructor(private formbuilder: FormBuilder, private validador: ValidadoresService)
  {
    this.crearFormulario();
    this.cargarDataFormulario();
    this.crearListeners();
  }

  ngOnInit(): void
  {

  }

  get nombreNoValido()
  {
    // Envía este validador al html
    return this.forma.get('nombre').invalid && this.forma.get('nombre').touched;
  }

  get apellidoNoValido()
  {
    // Envía este validador al html
    return this.forma.get('apellido').invalid && this.forma.get('apellido').touched;
  }

  get emailNoValido()
  {
    // Envía este validador al html
    return this.forma.get('email').invalid && this.forma.get('email').touched;
  }

  get usuarioNoValido()
  {
    // Envía este validador al html
    return this.forma.get('usuario').invalid && this.forma.get('usuario').touched;
  }

  get barrioNoValido()
  {
    // Envía este validador al html
    return this.forma.get('direccion.barrio').invalid && this.forma.get('direccion.barrio').touched;
  }

  get ciudadNoValido()
  {
    // Envía este validador al html
    return this.forma.get('direccion.ciudad').invalid && this.forma.get('direccion.ciudad').touched;
  }

  get pasatiempos()
  {
    return this.forma.get('pasatiempos') as FormArray;
  }

  get passwordNoValido()
  {
    return this.forma.get('password').invalid && this.forma.get('password').touched;
  }

  get passwordConfirmNoValido()
  {
    const password = this.forma.get('password').value;
    const passwordConfirm = this.forma.get('passwordConfirm').value;

    return ( password === passwordConfirm ) ? false : true;
  }



  crearFormulario()
  {
    /**
     * Primero va el string, despues el validador sincrono y tercero el validador asincrono.
     * Cuando se van a agregar dos o mas validators, se deben agregar en []
     */
    this.forma= this.formbuilder.group({
      nombre   : ['', [Validators.required, Validators.minLength(5)]],
      apellido : ['', [Validators.required, Validators.minLength(5), this.validador.noApellido]],
      email    : ['', [Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$'), Validators.required]],
      // dejamos separacion de coma porque el segundo argumento es un validador sincrono y el tercero un asyncrono
      usuario  : ['', /*acá pueden ir validadores sincronos*/ , this.validador.existeUsuario],
      password: ['', Validators.required],
      passwordConfirm: ['', Validators.required],
      direccion: this.formbuilder.group({
        barrio: ['', Validators.required],
        ciudad: ['', Validators.required]
      }),
      pasatiempos: this.formbuilder.array([])
    }, {
      validators: this.validador.passwordsIguales('password', 'passwordConfirm')
    });
  }

  /**
   * Podemos saber cuando la forma sufra cualquier cambio
   */
  crearListeners()
  {
    /* this.forma.valueChanges.subscribe( valor => console.log(valor));

    this.forma.statusChanges.subscribe( status => console.log({status})); */

    /**
     * Para validar un campo en especifico
     */
    this.forma.get('nombre').valueChanges.subscribe( val => console.log(val));
  }

  cargarDataFormulario()
  {
     // this.forma.setValue({
     this.forma.reset({
      nombre: '',
      apellido: '',
      email: '',
      direccion: {
        barrio: '',
        ciudad: ''
      }
    });
  }

  agregarPasatiempo()
  {
    // se pueden agregar los validators para el campo
    this.pasatiempos.push( this.formbuilder.control('') );
  }

  borrarPasatiempo(i: number)
  {
    this.pasatiempos.removeAt(i);
  }



  guardar()
  {
    // ejecutamos todas las validaciones simultaneamente
    if (this.forma.invalid)
    {
      return Object.values( this.forma.controls ).forEach( control => {
        if (control instanceof FormGroup)
        {
          Object.values( control.controls ).forEach(control => control.markAllAsTouched());
        }
        else
        {
          control.markAsTouched();
        }
      });
    }
    // poste de información
    this.forma.reset({

    });

  }

}
