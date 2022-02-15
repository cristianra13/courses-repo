import { Component, OnInit, ViewChild } from '@angular/core';
import { Alumno } from '../../../models/alumno';
import { Curso } from '../../../models/curso';
import { Examen } from '../../../models/examen';
import { ActivatedRoute } from '@angular/router';
import { AlumnoService } from '../../../services/alumno.service';
import { CursoService } from '../../../services/curso.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ResponderExamenModalComponent } from '../responder-examen-modal/responder-examen-modal.component';
import { RespuestaService } from '../../../services/respuesta.service';
import { Respuesta } from '../../../models/respuesta';
import Swal from 'sweetalert2';
import { VerExamenModalComponent } from '../ver-examen-modal/ver-examen-modal.component';

@Component({
  selector: 'app-responder-examen',
  templateUrl: './responder-examen.component.html',
  styleUrls: ['./responder-examen.component.css']
})
export class ResponderExamenComponent implements OnInit {

  alumno: Alumno;
  curso: Curso;
  examenes: Examen[] = [];
  dataSource: MatTableDataSource<Examen>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  pageSizeOptions: number[] = [3, 5, 10, 25, 100];
  mostrarColumnasLista: string[] = ['id', 'nombre', 'asignatura', 'preguntas', 'responder', 'ver'];

  constructor(private route: ActivatedRoute,
              private alumnoService: AlumnoService,
              private cursoService: CursoService,
              private respuestaService: RespuestaService,
              public dialog: MatDialog) {

              }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = +params.get('id');
      this.alumnoService.detalle(id).subscribe(alumno => {
        this.alumno = alumno;

        this.cursoService.obtenerCursoPorAlumnoId(alumno).subscribe(curso => {
          this.curso = curso;
          this.examenes = (curso && curso.examenes) ? curso.examenes : [];

          this.dataSource = new MatTableDataSource<Examen>(this.examenes);
          this.dataSource.paginator = this.paginator;
          this.paginator._intl.itemsPerPageLabel = 'Registros por página';
        });
      });
    });

  }

  responderExamen(examen: Examen) {
    /**
     * Pasamos la pagina que va a ser el modal, y pasamos las configuraciones de está
     */
    const modalRef = this.dialog.open(ResponderExamenModalComponent, {
      width: '750px',
      data: { curso: this.curso, alumno: this.alumno, examen: examen }
    });

     modalRef.afterClosed().subscribe((respuestasMap: Map<number, Respuesta>) => {
      if(respuestasMap) {
          // convertimos el map que se devuelve a un arreglo para enviar al backend
          const respuestas = Array.from(respuestasMap.values());
          console.log(respuestas);
          this.respuestaService.crear(respuestas).subscribe(resp => {
            examen.respondido = true;
            Swal.fire(
              'Enviado',
              'El examen fue enviado con exito',
              'success'
            );
          });
      }
    });
  }

  verExamen(examen: Examen) {
    this.respuestaService.obtenerRespuestasPorAlumnoPorExamen(this.alumno, examen)
      .subscribe(resp => {
        // enviamos el nbombre del componente que va a representar este examen
        const modalRef = this.dialog.open(VerExamenModalComponent, {
          width: '750px',
          data: { curso: this.curso, examen: examen, respuestas: resp }
        });
        /**
         * Como no enviamos data al componente, no hacemos alguna logica, solo cerramos el modal
         */
        modalRef.afterClosed().subscribe(() => {});
      });
  }

}
