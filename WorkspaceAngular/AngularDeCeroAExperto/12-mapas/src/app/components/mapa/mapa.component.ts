import { Component, OnInit, EventEmitter } from '@angular/core';
import { Marcador } from '../../classes/marcador.class';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef} from '@angular/material/dialog';
import { MapaEditarComponent } from './mapa-editar.component';


@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styles: [
  ]
})
export class MapaComponent implements OnInit
{
  marcadores: Marcador[] = [];

  lat = 51.678418;
  lng = 7.809007;

  constructor(private snackBar: MatSnackBar, public dialog: MatDialog)
  {
    if(localStorage.getItem('marcadores'))
    {
      this.marcadores = JSON.parse(localStorage.getItem('marcadores'));
    }
  }

  ngOnInit(): void {
  }

  agregarMarcador( evento ) {

    console.log(evento);

    const coords: { lat: number, lng: number } = evento.coords;

    const nuevoMarcador = new Marcador( coords.lat, coords.lng );

    this.marcadores.push( nuevoMarcador );

    this.guardarStorage();
    // dentro de los corchetes mandamos la configuracion del popup
    this.snackBar.open('Marcador agregado', 'Cerrar', { duration: 3000 });

  }

  editarMarcador(marcador: Marcador)
  {
    // con MapaEditarComponent carga el HTML que tiene ese componente dentro del modal de editar
    const dialogRef = this.dialog.open(MapaEditarComponent, {
      // el tamaño del modal
      width: '250px',
      // la data que vamos a mandar
      data: {titulo: marcador.titulo, desc: marcador.desc}
    });

    // result es la información que viene del modal de editar
    dialogRef.afterClosed().subscribe( result => {
      if (!result)
      {
        return;
      }

      marcador.titulo = result.titulo;
      marcador.desc = result.desc;
      this.guardarStorage();
      this.snackBar.open('Marcador actualizado', 'Cerrar', { duration: 3000 });

    });
  }

  borrarMarcador(indice: number)
  {
    this.marcadores.splice(indice, 1);
    this.guardarStorage();
    this.snackBar.open('Marcador borrado', 'Cerrar' , { duration: 3000 });
  }

  // guardamos los marcadores en el localstorage
  guardarStorage()
  {
    localStorage.setItem('marcadores', JSON.stringify(this.marcadores));
  }

}
