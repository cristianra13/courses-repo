import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MoviesService } from '../../services/movies.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router)
  {

  }

  ngOnInit(): void {
  }

  searchMovie(txtSearch: string)
  {
    txtSearch = txtSearch.trim();
    if (txtSearch.length === 0)
    {
      return;
    }

    this.router.navigate(['/search', txtSearch]);

    console.log(txtSearch);

  }

}
