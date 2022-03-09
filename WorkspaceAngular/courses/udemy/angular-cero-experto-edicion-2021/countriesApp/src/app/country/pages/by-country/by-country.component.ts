import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CountryService } from '../../services/country.service';
import { Country } from '../../interfaces/country.interface';

@Component({
  selector: 'app-by-country',
  templateUrl: './by-country.component.html',
  styles: [`
    li {
      cursor: pointer;
    }

  `]
})
export class ByCountryComponent {

  countries: Country[] = [];
  suggestCountries: Country[] = [];
  searchTerm: string = '';
  hasError: boolean = false;
  showSuggest: boolean = false;
  @Output() placeholder: EventEmitter<string>  = new EventEmitter();

  constructor(private countryService: CountryService) {

  }

  search(searchTerm: string) {
    this.showSuggest = false;
    this.hasError = false;
    // el termino que viene del input, lo seteamos a la variable de la clase this.searchTerm
    this.searchTerm = searchTerm;
    this.countryService.searchCountry(searchTerm)
      .subscribe({
        next: (countries) =>  {
          this.countries = countries;
      },
      error: (err) => {
        this.hasError = true;
      }});

  }

  suggest(term: string) {
    this.hasError = false;
    this.searchTerm = term;
    this.showSuggest = true;

    this.countryService.searchCountry(term)
      .subscribe(
        countries => this.suggestCountries = countries.splice(0, 5),
        (error) => this.suggestCountries = []
      );

  }

  searchSuggest(term: string) {
    this.search(term);

  }


}
