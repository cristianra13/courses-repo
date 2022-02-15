import { OnInit, Directive } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'

import { CommonService } from '../services/common.service';
import { Generic } from '../models/generic';

@Directive()
export abstract class CommonFormComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  // atributos de forma generica
  protected redirect: string;
  protected nombreModel: string;
  titulo: string;
  model: E;
  error: any;

  constructor(protected service: S,
    protected router: Router,
    protected route: ActivatedRoute) { }

  ngOnInit(): void {
    // obtenemos el id del alumno
    this.route.paramMap.subscribe(params => {
      /**
       * con + lo convertimos de string a numero
       * Adicional, se encuentra escuchando al momento de cambio de la ruta con los parametros
       */
      const id: number = +params.get('id');

      if(id){
        this.service.detalle(id).subscribe(model => {
          this.model = model;
          this.titulo = `Editar ${this.nombreModel}`;
        });
      }
    });
  }

  crear(){
    this.service.crear(this.model).subscribe(modelCreado => {

      console.log(modelCreado);
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

    Swal.fire({
      icon: 'info',
      title: 'Esta seguro(a) de actualizar el registro?',
      showDenyButton: true,
      // showCancelButton: true,
      confirmButtonText: `Actualizar`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.service.editar(this.model).subscribe(modelCreado => {

          console.log(modelCreado);
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Actualizado correctamente!',
            showConfirmButton: false,
            timer: 1000
          });
          this.router.navigate([this.redirect]);

        }, err => {

          if(err.status === 400){
            this.error = err.error;
            console.log(this.error);
          }

        });
        Swal.fire('Actualizado correctamente!', '', 'success')
      }
    });
  }
}
