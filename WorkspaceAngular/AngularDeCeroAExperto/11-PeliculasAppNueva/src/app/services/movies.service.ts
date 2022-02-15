import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { NowPlayingResponse, Movie } from '../interfaces/cartelera-response';
import { MovieDetail } from '../interfaces/movie-response-detail';
import { CreditsResponse, Cast } from '../interfaces/credits-response';

@Injectable({
  providedIn: 'root'
})
export class MoviesService
{
  private urlRestMovies = 'http://localhost:8080';
  private nowPlayingMoviesPage = 1;
  public loading: boolean = false;

  constructor(private http: HttpClient) { }

  getNowPlayingMovies(): Observable<Movie[]>
  {
    if (this.loading)
    {
      return;
    }

    console.log('Cargando API');
    this.loading = true;

    return this.http.get<NowPlayingResponse>(`${this.urlRestMovies}/getNowPlayingMovies/${this.nowPlayingMoviesPage}`).pipe(
      map( (response) =>  response.results),
      tap( () => {
      this.nowPlayingMoviesPage += 1; // aumentamos el valor de la pagina por cada llamado
      this.loading = false;
    }) );
  }

  searchMovies(txtSearch: string)
  {
    return this.http.get<NowPlayingResponse>(`${this.urlRestMovies}/searchMovie/${txtSearch}`).pipe(
      map( (response) => response.results),
      // Si no existe la pelicula o el id es incorrecto, se regresa un null en el error
      catchError(err => of(null))
      );
  }

  getMovieDetail(id: string)
  {
    return this.http.get<MovieDetail>(`${this.urlRestMovies}/getDetail/${id}`);
  }

  getMovieCredits(id: string): Observable<Cast[]> // va a retornar un observable que emite un Cast[]
  {
    return this.http.get<CreditsResponse>(`${this.urlRestMovies}/getCredits/${id}`).pipe(
      map(response => response.cast),
      catchError(err => of([]))
      );
  }

  resetPage()
  {
    this.nowPlayingMoviesPage = 1;
  }

}
