import { Directive, OnInit, ViewChild } from '@angular/core';
import Swal from 'sweetalert2'
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';

@Directive()
export abstract class CommonListarComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  protected nombreModel: string;

  titulo: string;
  lista: E[] = [];
  totalRegistros = 0;
  paginaActual = 0;
  totalPorPagina = 4;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(protected service: S) { }

  ngOnInit(): void {
    this.calcularRangos();
  }

  paginar(event: PageEvent){
    this.paginaActual= event.pageIndex;
    this.totalPorPagina = event.pageSize;

    this.calcularRangos();
  }

  private calcularRangos(){
    this.service.listarPaginado(this.paginaActual.toString(), this.totalPorPagina.toString()).subscribe(listaPag => {
      this.lista = listaPag.content as E[];
      this.totalRegistros = listaPag.totalElements as number;
      this.paginator._intl.itemsPerPageLabel = 'Items por página'
    });
  }

  eliminar(e: E){

    Swal.fire({
      icon: 'info',
      title: 'Esta seguro(a) de eliminar el registro?',
      showDenyButton: true,
      denyButtonColor: '#2778c4',
      confirmButtonText: `Eliminar`,
      confirmButtonColor: '#d14529'
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {

        this.service.eliminar(e.id).subscribe(() =>{
          this.calcularRangos();

          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Eliminado correctamente!',
            showConfirmButton: false,
            timer: 1000
          });

        }, error => {

          if(error.status === 400 || error.status === 500){
            Swal.fire({
              icon: 'error',
              title: 'Ocurrio un error',
              text: 'Contacte al administrador'
              //footer: '<a href>Why do I have this issue?</a>'
            });
          }

        });
      }
    });
  }

}
