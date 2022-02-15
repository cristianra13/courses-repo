import { Component, OnInit } from '@angular/core';
import { FileItem } from '../../models/file-item.model';
import { CargaImagenesService } from '../../services/carga-imagenes.service';

@Component({
  selector: 'app-carga',
  templateUrl: './carga.component.html',
  styles: [
  ]
})
export class CargaComponent implements OnInit
{
  estaSobreDrop = false;
  archivos: FileItem[] = [];

  constructor(private cis: CargaImagenesService) { }

  ngOnInit(): void {
  }

  cargarImagenes()
  {
    this.cis.cargarImagenesFirebase(this.archivos);
  }

  limpiarArchivos()
  {
    this.archivos = [];
  }

}
