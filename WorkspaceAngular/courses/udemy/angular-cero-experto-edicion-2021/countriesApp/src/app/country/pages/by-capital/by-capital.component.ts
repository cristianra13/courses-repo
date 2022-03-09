import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Country } from '../../interfaces/country.interface';
import { CountryService } from '../../services/country.service';

@Component({
  selector: 'app-by-capital',
  templateUrl: './by-capital.component.html',
  styles: [
  ]
})
export class ByCapitalComponent implements OnInit {

  countries: Country[] = [];
  searchTerm: string = 'Hello World';
  hasError: boolean = false;
  @Output() placeholder: EventEmitter<string> = new EventEmitter();

  constructor(private countryService: CountryService) { }

  ngOnInit(): void {
  }

  search(searchTerm: string) {
    this.hasError = false;
    // el termino que viene del input, lo seteamos a la variable de la clase this.searchTerm
    this.searchTerm = searchTerm;
    this.countryService.searchByCapitalCity(searchTerm)
      .subscribe({
        next: (countries) =>  {
          this.countries = countries;
      },
      error: (err) => {
        this.hasError = true;
      }});

  }

}
