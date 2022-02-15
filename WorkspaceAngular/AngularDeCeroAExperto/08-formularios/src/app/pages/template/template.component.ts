import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PaisService } from '../../services/pais.service';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.css']
})
export class TemplateComponent implements OnInit {

  usuario = {
    nombre: '',
    apellido: '',
    elsemail: '',
    pais: ''
  };

  paises: any[] = [];

  constructor(private paisService: PaisService) { }

  ngOnInit(): void
  {
    this.paisService.getPaises().subscribe(paises => {
      this.paises = paises;
      this.paises.unshift({
        nombre: '[Seleccione País]',
        codigo: ''
      });
    });
  }

  guardar(form: NgForm)
  {
    console.log(form.value);
    if (form.invalid)
    {
      /**
       * Vamos a decir que todos los campos han sido tocados o manipulados para cuando se haga el submit, y no haya nada en lso campos
       * lance el respectivo error de cada uno
       */
      Object.values(form.controls).forEach(control => {
        control.markAllAsTouched();
      });
      return;
    }
  }

}
