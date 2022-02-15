import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MoviesService } from '../../services/movies.service';
import { MovieDetail } from '../../interfaces/movie-response-detail';
import { Location } from '@angular/common';
import { CreditsResponse, Cast } from '../../interfaces/credits-response';
import { combineLatest } from 'rxjs';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  public movie: MovieDetail;
  public cast: Cast[] = [];

  constructor(private activatedRoute: ActivatedRoute,
              private service: MoviesService,
              private location: Location,
              private router: Router) { }

  ngOnInit(): void
  {
    // get params from url
    // const id = this.activatedRoute.snapshot.params.id;

    // desde desestructuración podemos extraer los parametros de la url
    const {id} = this.activatedRoute.snapshot.params;

    // combineLatest recibe una cantidad n de observables y regresa un objeto que es un arreglo con todas
    // las respuestas de los observables cuando ya han emitido por lo menos un valor, todos!
    combineLatest([
      this.service.getMovieDetail(id),
      this.service.getMovieCredits(id)
    ]).subscribe(( [movie, cast] ) => { // desestructuración de argumentos
      if (!movie)
      {
        return;
      }
      this.movie = movie;
      this.cast = cast.filter(actor => actor.profile_path != null);
    });

    /* this.service.getMovieDetail(id).subscribe(movie => {
      if (!movie)
      {
        this.router.navigateByUrl('/home');
        console.log('paso redirect');
        return;
      }
      this.movie = movie;
    });

    this.service.getMovieCredits(id).subscribe(cast => {
      // filter regresa un nuevo arreglo
      this.cast = cast.filter(actor => actor.profile_path != null);
    }); */
  }

  onBack()
  {
    this.location.back();
  }

}
