import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { UsuarioModel } from '../../models/usuario.model';
import { AuthService } from '../../services/auth.service';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit
{
  usuario: UsuarioModel;
  recordarUsuario = false;

  constructor(private authService: AuthService, private router: Router)
  {

  }

  ngOnInit()
  {
    this.usuario = new UsuarioModel();
  }

  onSubmit( form: NgForm )
  {
    if(form.invalid)
    {
      return;
    }

    Swal.fire({
      // title: 'Error!',
      text: 'Espere por favor',
      //icon: 'error',
      // confirmButtonText: 'Cool',
      allowOutsideClick: false // no permite cerrar la alerta al dar clic fuera del modal
    });

    Swal.showLoading();

    this.authService.registrarNuevoUsuario(this.usuario).subscribe( response =>
    {
      Swal.close();

      if (this.recordarUsuario)
      {
        localStorage.setItem('email', this.usuario.email);
      }
      this.router.navigateByUrl('/home');
    },
    (err) => {
      Swal.fire({
        title: 'Error al registrar usuario',
        text: err.error.error.message,
        icon: 'error',
        // confirmButtonText: 'Cool',
        allowOutsideClick: false // no permite cerrar la alerta al dar clic fuera del modal
      });
    });
  }

}
