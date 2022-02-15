import { Component, OnInit, ViewChild } from '@angular/core';
import { Curso } from '../../../models/curso';
import { ActivatedRoute } from '@angular/router';
import { CursoService } from '../../../services/curso.service';
import { AlumnoService } from '../../../services/alumno.service';
import { Alumno } from '../../../models/alumno';
import { SelectionModel } from '@angular/cdk/collections';
import Swal from 'sweetalert2';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-asignar-alumnos',
  templateUrl: './asignar-alumnos.component.html',
  styleUrls: ['./asignar-alumnos.component.css']
})
export class AsignarAlumnosComponent implements OnInit {

  tabIndex = 0;
  curso: Curso;
  alumnosAsignar: Alumno[] = [];
  alumnos: Alumno[] = [];
  mostrarColumnas: string[] = ['nombre', 'apellido', 'seleccion'];
  mostrarColumnasLista: string[] = ['id', 'nombre', 'apellido', 'email', 'eliminar'];
  dataSource: MatTableDataSource<Alumno>;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  /**
   * debemos indicar a SelectionModel<Alumno> que la seleccion de los elementos son de tipo alumno,
   * adicional, en el contructor indicamos con true que permite seleccion multiple,y pasamos un arreglo
   * vacío donde se van a ir almacenando los alumnos
   */
  seleccion: SelectionModel<Alumno> = new SelectionModel(true, []);

  constructor(private route: ActivatedRoute,
              private cursoService: CursoService,
              private alumnoService: AlumnoService) {

              }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      this.cursoService.detalle(id).subscribe(curso => {
        this.curso = curso;
        this.alumnos = this.curso.alumnos;
        this.initPaginator();
      });
    });
  }


  filtrar(nombre: string) {
    nombre = nombre !== undefined ? nombre.trim() : '';
    if(nombre !== ''){
      this.alumnoService.buscar(nombre).subscribe(alumnos => this.alumnosAsignar = alumnos.filter( a => {
        let filtrar = true;
        // comparamos cada alumno de la busqueda por cada alumno del curso para omitir los que ya están en el curso
        this.alumnos.forEach(cursoAlumno => {
          if(a.id === cursoAlumno.id) {
            filtrar = false;
          }
        });
        return filtrar;
      }));
    }

  }

  todosSeleccionados(): boolean {
    const seleccionados = this.seleccion.selected.length;
    const numeroAlumnos = this.alumnosAsignar.length;
    return (seleccionados === numeroAlumnos);
  }

  seleccionarDesSeleccionarTodos() {
    this.todosSeleccionados() ? this.seleccion.clear() : this.alumnosAsignar.forEach(alumno => this.seleccion.select(alumno));
  }

  asignar() {
    this.cursoService.asignarAlumnos(this.curso, this.seleccion.selected).subscribe(curso => {
      // con este valor se va a al index 2, es decir, a alumnos del tab
      this.tabIndex = 2;
      Swal.fire('Asignados', `Alumnos asignados con exito al curso ${curso.nombre}`, 'success');
      this.alumnos = this.alumnos.concat(this.seleccion.selected);
      this.initPaginator();
      this.alumnosAsignar = [];
      this.seleccion.clear();
    }, (err) => {
      if(err.status === 500){
        const mensajeError = err.error.message as string;
        if(mensajeError.indexOf('ConstraintViolationException')){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrio un error al asignar el alumno!',
            footer: `<strong>El alumno ya se encuentra registrado a otro curso</strong>`
          });
        }
      }
    });
  }

  eliminarAlumno(alumno: Alumno) {

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
        this.cursoService.eliminarAlumnoCurso(this.curso, alumno).subscribe(curso => {
          this.alumnos = this.alumnos.filter(al => al.id !== alumno.id);
          this.initPaginator();
          Swal.fire('Eliminado', `Alumno ${alumno.nombre} ${alumno.apellido} ha sido eliminado del curso ${this.curso.nombre}`, 'success');
        }, err => {
          if(err.status === 500){
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Ocurrio un error al eliminar el alumno!',
              footer: 'Contacte con el administrador para mas información'
            });
          }
        });
      }
    });
  }

  initPaginator() {
    this.dataSource = new MatTableDataSource<Alumno>(this.alumnos);
    this.dataSource.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'Registros por pagina';
  }

}
