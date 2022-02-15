import { Injectable } from '@angular/core';
import { HttpClient, JsonpInterceptor } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PeliculasService
{

  private apiKey = '1aa0eda58930940bb59170176807b901';
  private urlMoviedb = 'https://api.themoviedb.org/3';

  peliculas: any[] = [];

  constructor(private http: HttpClient)
  {

  }

  getCartelera()
  {
    let fechaDesde = new Date();
    let fechahasta = new Date();

    fechahasta.setDate( fechahasta.getDate() + 7 );
    let desdeStr = `${fechaDesde.getFullYear()}-${fechaDesde.getMonth() + 1}-${fechaDesde.getDay}`;
    let hastaStr = `${fechaDesde.getFullYear()}-${fechaDesde.getMonth() + 1}-${fechaDesde.getDay}`;

    let url = `${this.urlMoviedb}/movie/now_playing?api_key=${this.apiKey}&primary_release_date.get=${desdeStr}&primary_release_date.get=${hastaStr}`;
    return this.http.get(url).pipe( map( (response: any) => {
      return response.results;
    }));
  }

  getPopular()
  {
    let url = `${this.urlMoviedb}/movie/popular?api_key=${this.apiKey}&language=en-US&sort_by=popularity.desc&language=es&page=1`;
    return this.http.get(url).pipe( map( (response: any) => {
      return response.results;
    }));
  }

  getUpcoming()
  {
    let url = `${this.urlMoviedb}/movie/upcoming?api_key=${this.apiKey}&language=es-MX&page=1`;
    return this.http.get(url).pipe( map( (response: any) => {
      return response.results;
    }));
  }

  searchMovie(text: string)
  {
    let url = `${this.urlMoviedb}/search/movie?query=${text}&api_key=${this.apiKey}&language=es`;

    return this.http.get(url).pipe( map( (resp: any) =>
    {
      this.peliculas = resp.results;
      console.log(this.peliculas);

      return resp.results;
    }
    ));
  }

  getpelicula(id: string)
  {
    let url = `${this.urlMoviedb}/movie/${id}?api_key=${this.apiKey}&language=es`;
    return this.http.get(url).pipe( map( (response: any) => {
      return response;
    }));
  }

}
