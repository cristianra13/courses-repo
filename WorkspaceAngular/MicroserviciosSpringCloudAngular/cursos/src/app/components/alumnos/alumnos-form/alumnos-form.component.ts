import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'

import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from '../../../services/alumno.service';
import { CommonFormComponent } from '../../common-form.component';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent extends CommonFormComponent<Alumno, AlumnoService> {

  private fotoSeleccionada: File;

  constructor(service: AlumnoService, router: Router, route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Alumnos';
    this.model = new Alumno();
    this.redirect = '/alumnos';
    this.nombreModel = Alumno.name;
  }

  seleccionarFoto(event){
    this.fotoSeleccionada = event.target.files[0];
    if(this.fotoSeleccionada.type.indexOf('image') < 0){
      this.fotoSeleccionada = null;
      Swal.fire({
        icon: 'error',
        title: 'Ocurrio un error',
        text: 'Formato de archivo no permitido'
        //footer: '<a href>Why do I have this issue?</a>'
      })
    }
  }

  crear(){
    if(!this.fotoSeleccionada){
      super.crear();
    }else{
      this.crearConFoto();
    }
  }

  crearConFoto(){
    this.service.crearConFoto(this.model, this.fotoSeleccionada).subscribe(alumno => {

      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Creado correctamente!',
        showConfirmButton: false,
        timer: 1000
      })
      this.router.navigate([this.redirect]);

    }, err => {

      if(err.status === 400){
        this.error = err.error;
        console.log(this.error);
        Swal.fire({
          icon: 'error',
          title: 'Ocurrio un error',
          text: 'Contacte al administrador'
          //footer: '<a href>Why do I have this issue?</a>'
        })
      }

    });
  }

  editar(){
    if(!this.fotoSeleccionada){
      super.editar();
    }else{

      Swal.fire({
        icon: 'info',
        title: 'Esta seguro(a) de actualizar el registro?',
        showDenyButton: true,
        // showCancelButton: true,
        confirmButtonText: `Actualizar`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          this.service.editarConFoto(this.model, this.fotoSeleccionada).subscribe(usuarioAct => {

            console.log(usuarioAct);
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Actualizado correctamente!',
              showConfirmButton: false,
              timer: 1000
            });
            this.router.navigate([this.redirect]);

          }, err => {

            if(err.status === 400 || err.status === 500){
              this.error = err.error;
              console.log(this.error);
              Swal.fire({
                icon: 'error',
                title: 'Ocurrio un error',
                text: 'Contacte al administrador'
                //footer: '<a href>Why do I have this issue?</a>'
              });
            }

          });
          Swal.fire('Actualizado correctamente!', '', 'success')
        }
      });
    }

    }
}
