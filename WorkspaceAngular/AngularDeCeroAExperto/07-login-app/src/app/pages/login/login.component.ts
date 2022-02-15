import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UsuarioModel } from '../../models/usuario.model';
import { AuthService } from '../../services/auth.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: UsuarioModel = new UsuarioModel();
  recordarUsuario = false;

  constructor(private auth: AuthService, private router: Router)
  {

  }

  ngOnInit()
  {
    if (localStorage.getItem('email'))
    {
      this.usuario.email = localStorage.getItem('email');
      this.recordarUsuario = true;
    }
  }

  login(form: NgForm)
  {
    if(form.valid)
    {
      Swal.fire({
        // title: 'Error!',
        text: 'Espere por favor',
        //icon: 'error',
        // confirmButtonText: 'Cool',
        allowOutsideClick: false // no permite cerrar la alerta al dar clic fuera del modal
      });

      Swal.showLoading();

      this.auth.login(this.usuario).subscribe( resp => {
        Swal.close();

        if (this.recordarUsuario)
        {
          localStorage.setItem('email', this.usuario.email);
        }

        this.router.navigateByUrl('/home');

      }, (err) => {
        Swal.fire({
          title: 'Error al autenticar',
          text: err.error.error.message,
          icon: 'error',
          // confirmButtonText: 'Cool',
          allowOutsideClick: false // no permite cerrar la alerta al dar clic fuera del modal
        });

      });
    }
  }

}
