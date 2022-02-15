import { Component, Inject, OnInit } from '@angular/core';
import { Curso } from '../../../models/curso';
import { Alumno } from '../../../models/alumno';
import { Examen } from '../../../models/examen';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pregunta } from '../../../models/pregunta';
import { Respuesta } from '../../../models/respuesta';

@Component({
  selector: 'app-responder-examen-modal',
  templateUrl: './responder-examen-modal.component.html',
  styleUrls: ['./responder-examen-modal.component.css']
})
export class ResponderExamenModalComponent implements OnInit {

  curso: Curso;
  alumno: Alumno;
  examen: Examen;

  /**
   * El atrubuto de respuesta que enviamos al cerrar el modal
   * en este caso el id de la respuesta mas la pregunta
   */
  respuestas = new Map<number, Respuesta>();

  constructor( @Inject(MAT_DIALOG_DATA) public data: any, public modalRef: MatDialogRef<ResponderExamenModalComponent> ) {

  }

  ngOnInit(): void {
    /**
     * Estos datos se pasan desde la clase de responder examen por MatDialog
     *
     * Deben ser los mismos nombres en this.data.XXXXX
     */
    this.curso = this.data.curso as Curso;
    this.alumno = this.data.alumno as Alumno;
    this.examen = this.data.examen as Examen;
  }

  responder(pregunta: Pregunta, event: any) {
    const texto = event.target.value;
    const respuesta = new Respuesta();
    respuesta.alumno = this.alumno;
    respuesta.pregunta = pregunta;

    /**
     * creamos una constante de examen para poder asignar el nombre y Id ya que si se hace de está manera
     * respuesta.pregunta.examen = this.examen;
     * genera un error de conversión circular de json ya que cada pregunta tiene el examen y cada examen tiene la preguntas
     */
    const examen = new Examen();
    examen.id = this.examen.id;
    examen.nombre = this.examen.nombre;
    respuesta.pregunta.examen = examen;
    respuesta.respuesta = texto;

    this.respuestas.set(pregunta.id, respuesta);
    console.log(this.respuestas);
  }

  cancelar() {
    this.modalRef.close();
  }



}
