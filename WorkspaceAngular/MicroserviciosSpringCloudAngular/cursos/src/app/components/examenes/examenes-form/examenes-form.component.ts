import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../../common-form.component';
import { Examen } from '../../../models/examen';
import { ExamenService } from '../../../services/examen.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Asignatura } from '../../../models/asignatura';
import { Pregunta } from 'src/app/models/pregunta';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-examenes-form',
  templateUrl: './examenes-form.component.html',
  styleUrls: ['./examenes-form.component.css']
})
export class ExamenesFormComponent extends CommonFormComponent<Examen, ExamenService> implements OnInit {

  public asignaturasPadre: Asignatura[] = [];
  public asignaturasHijas: Asignatura[] = [];
  errorPreguntas: string;

  constructor(service: ExamenService, router: Router, route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Curso';
    this.model = new Examen();
    this.redirect = '/examenes';
    this.nombreModel = Examen.name;
  }

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
          /* this.service.findAllAsignaturas().subscribe(
            asignaturas => this.asignaturasHijas = asignaturas
            .filter(a => a.asignaturaPadre && a.asignaturaPadre.id === this.model.asignaturaPadre.id)); */
          this.cargarAsignaturasHijas();
        });
      }
    });

    // Agregamos las asignaturas padrs siempre y cuando en el filtro estás no tengan un padre
    this.service.findAllAsignaturas().subscribe(asig => {
      console.log(asig);
      this.asignaturasPadre = asig.filter(a => !a.asignaturaPadre);
    });
  }

  crear() {
    if (this.model.preguntas.length  === 0) {
      // Swal.fire('Error preguntas', 'Examen debe tener preguntas', 'error');
      this.errorPreguntas = 'Examen debe tener preguntas';
      return;
    }
    this.errorPreguntas = undefined;
    this.eliminaPreguntasVacias();
    super.crear();
  }

  editar() {
    if (this.model.preguntas.length  === 0) {
      //Swal.fire('Error preguntas', 'Examen debe tener preguntas', 'error');
      this.errorPreguntas = 'Examen debe tener preguntas';
      return;
    }
    this.errorPreguntas = undefined;
    this.eliminaPreguntasVacias();
    super.editar();
  }

  cargarAsignaturasHijas(){
    this.asignaturasHijas = this.model.asignaturaPadre ? this.model.asignaturaPadre.asignaturasHijos : [];
  }

  compararAsignatura(asig1: Asignatura, asig2: Asignatura): boolean {
    if (asig1 === undefined && asig2 === undefined){
      return true;
    }

    if (asig1 === null || asig2 === null  || asig1 === undefined || asig2 === undefined) {
      return false;
    }

    if(asig1.id === asig2.id){
      return true;
    }

    return (asig1 === null || asig2 === null  || asig1 === undefined || asig2 === undefined) ? false : asig1.id === asig2.id;
  }

  agregarPregunta() {
    this.model.preguntas.push(new Pregunta());
  }

  asignarTexto(pregunta: Pregunta, event: any) {
    pregunta.descripcion = event.target.value;
    console.log(this.model);
  }

  eliminarPregunta(pregunta: Pregunta) {
    this.model.preguntas = this.model.preguntas.filter(p => pregunta.descripcion !== p.descripcion);
  }

  eliminaPreguntasVacias() {
    this.model.preguntas = this.model.preguntas.filter(p => p.descripcion != null && p.descripcion.length > 0);
  }

}
