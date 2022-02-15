import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MoviesService } from '../../services/movies.service';
import { Movie } from '../../interfaces/cartelera-response';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public text: string = '';
  public movies: Movie[] = [];

  constructor(private activatedRoute: ActivatedRoute,
              private service: MoviesService)
  { }

  ngOnInit(): void
  {
    this.activatedRoute.params.subscribe(params => {
      this.text = params.textToSearch;

      this.service.searchMovies(params.textToSearch).subscribe(movies => {
        this.movies = movies;
      });
    });
  }

}
