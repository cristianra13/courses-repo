import { Component, OnInit } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
import { SpotifyService } from '../../services/spotify.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent
{

  /* constructor(private http: HttpClient)
  {
    // Cuando se responda el servicio. vamos a tener la información disponible en la variable paises
    this.http.get('https://restcountries.eu/rest/v2/lang/es').
      subscribe( (paises: any) =>{
        console.log(paises);
        this.paises = paises;
      } );
  } */

  nuevasCanciones: any[] = [];
  loading: boolean;
  error = false;
  mensajeError: string;

  constructor(private spotifyService: SpotifyService)
  {
    this.loading = true;
    this.error = false;

    this.spotifyService.getNewreleases()
      .subscribe ( (data: any) => {
        this.nuevasCanciones = data;
        this.loading = false;
      }, ( errorService ) => {
        this.loading = false;
        this.error = true;
        console.log(errorService);
        this.mensajeError = errorService.error.error.message;
        console.log(this.mensajeError);

      });
  }
}
