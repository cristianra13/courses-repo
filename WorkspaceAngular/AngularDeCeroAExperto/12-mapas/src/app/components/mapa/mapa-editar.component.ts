import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-mapa-editar',
  templateUrl: './mapa-editar.component.html',
  styles: [
  ]
})
export class MapaEditarComponent implements OnInit {

  forma: FormGroup;

  constructor(
    public formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<MapaEditarComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any)
    {
      console.log(data);
      this.forma = formBuilder.group({
        titulo: data.titulo,
        desc: data.desc
      });

    }

  ngOnInit(): void
  {
  }

  guardarCambios()
  {
    this.dialogRef.close(this.forma.value);

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
