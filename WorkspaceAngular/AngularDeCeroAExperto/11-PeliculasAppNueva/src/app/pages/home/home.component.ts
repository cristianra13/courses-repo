import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { MoviesService } from '../../services/movies.service';
import { Movie, NowPlayingResponse } from '../../interfaces/cartelera-response';
import Swal from 'sweetalert2';
import { of } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy
{

  public movies: Movie[] = [];
  public moviesSlideShow: Movie[] = [];

  // va a estar escuchando un evento propio del host, e este caso el window y nos interesa el scroll
  @HostListener('window:scroll', ['$event'])
  onScroll() // metodo que se dispara cada vez que se haga scroll
  {
    const position = (document.documentElement.scrollTop || document.body.scrollTop) + 1300;
    const max = (document.documentElement.scrollHeight || document.body.scrollHeight);

    if (position > max)
    {
      if (this.service.loading)
      {
        // la función of sirve para emitir observables
        return of([]); // observable que emite un arreglo vacío
      }

      console.log('llamar servicio');
      this.service.getNowPlayingMovies().subscribe( response => {
        this.movies.push(...response);
      });
    }
  }

  constructor(private service: MoviesService) { }

  ngOnInit(): void
  {
    this.service.getNowPlayingMovies().subscribe(response => {
      this.movies = response;
      this.moviesSlideShow = response;
    });
  }

  ngOnDestroy()
  {
    this.service.resetPage();
  }

}
