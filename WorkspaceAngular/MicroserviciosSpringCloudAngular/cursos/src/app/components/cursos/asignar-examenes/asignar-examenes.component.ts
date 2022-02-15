import { Component, OnInit, ViewChild } from '@angular/core';
import { Curso } from '../../../models/curso';
import { ActivatedRoute, Router } from '@angular/router';
import { CursoService } from '../../../services/curso.service';
import { FormControl } from '@angular/forms';
import { Examen } from '../../../models/examen';

import { map, flatMap } from 'rxjs/operators';
import { ExamenService } from '../../../services/examen.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import Swal from 'sweetalert2';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-asignar-examenes',
  templateUrl: './asignar-examenes.component.html',
  styleUrls: ['./asignar-examenes.component.css']
})
export class AsignarExamenesComponent implements OnInit {

  curso: Curso;
  autocompleteControl = new FormControl();
  examenesFiltrados: Examen[] = [];
  examenesSeleccionados: Examen[] = [];
  examenes: Examen[] = [];
  dataSource: MatTableDataSource<Examen>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  mostrarColumnas = ['nombre', 'asignatura', 'eliminar'];
  mostrarColumnasLista: string[] = ['id', 'nombre', 'asignatura', 'eliminar'];
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];
  tabIndex = 0;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private cursoService: CursoService,
              private examenService: ExamenService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = +params.get('id');
      this.cursoService.detalle(id).subscribe(curso => {
        this.curso = curso;
        this.examenes = this.curso.examenes;
        this.initPaginator();
      });
    });
    this.autocompleteControl.valueChanges.pipe(
      // si el valor es de tipo objet (examen), lo convertimos en tipo string1
      map(valor => typeof valor === 'string'? valor : valor.nombre),
      // vamosd a buscarlo al backend
      flatMap(valor => valor ? this.examenService.filtarPorNombre(valor) : [])
    ).subscribe(examens => this.examenesFiltrados = examens);
  }

  initPaginator() {
    this.dataSource = new MatTableDataSource<Examen>(this.examenes);
    this.dataSource.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'Registros por página';
  }

  mostrarNombre(examen?: Examen) {
    return examen ? examen.nombre : '';
  }

  seleccionarExamen(event: MatAutocompleteSelectedEvent) {
    const examen = event.option.value as Examen;
    if(!this.existe(examen.id)){
      this.examenesSeleccionados = this.examenesSeleccionados.concat(examen);
      console.log(this.examenesSeleccionados);
    } else{
      Swal.fire(
        'Error: ',
        `El examen "${examen.nombre}" ya está asignado al curso`,
        'error'
      );
    }
    // Dejamos la caja de texto sin valor y con el foco en la misma
    this.autocompleteControl.setValue('');
    event.option.deselect();
    event.option.focus();
  }

  private existe(id: number): boolean {
    let existe = false;

    this.examenesSeleccionados.concat(this.examenes).forEach( ex => {
      if(id === ex.id){
        existe = true;
      }
    });

    return existe;
  }

  desAsignarExamenCurso(examen: Examen) {
    this.examenesSeleccionados = this.examenesSeleccionados.filter(ex => examen.id !== ex.id);
  }

  asignar() {
    this.cursoService.asignarExamenes(this.curso, this.examenesSeleccionados).subscribe(curso => {
      this.examenes = this.examenes.concat(this.examenesSeleccionados);
      this.initPaginator();
      this.examenesSeleccionados = [];
      Swal.fire(
        'Asignado(s):',
        `Examenes asignados al curso ${this.curso.nombre}`,
        'success'
      );
      this.tabIndex = 2;
    });
  }

  eliminarExamenCurso(examen: Examen) {

    Swal.fire({
      icon: 'warning',
      title: 'Está seguro de eliminar el alumno?',
      showDenyButton: true,
      denyButtonColor: '#2778c4',
      confirmButtonColor: '#d14529',
      confirmButtonText: `Eliminar`,
      denyButtonText: `No`,
    }).then((result) => {

      if(result.isConfirmed){
        this.cursoService.eliminarExamen(this.curso, examen).subscribe(curso => {
          this.examenes = this.examenes.filter(ex => ex.id !== examen.id);
          this.initPaginator();
          Swal.fire('Eliminado', `Examen ${examen.nombre} ha sido eliminado del curso ${this.curso.nombre}`, 'success');
        }, err => {
          if(err.status === 500){
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Ocurrio un error al eliminar el examen!',
              footer: 'Contacte con el administrador para mas información'
            });
          }
        });
      }
    });

  }


}
