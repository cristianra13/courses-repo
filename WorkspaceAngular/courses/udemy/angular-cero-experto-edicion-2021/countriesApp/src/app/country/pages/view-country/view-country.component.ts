import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap, tap } from 'rxjs';
import { CountryService } from '../../services/country.service';
import { Country } from '../../interfaces/country.interface';

@Component({
  selector: 'app-view-country',
  templateUrl: './view-country.component.html',
  styles: [
  ]
})
export class ViewCountryComponent implements OnInit {

  country!: Country;

  constructor(private activatedRoute: ActivatedRoute,
              private countryService: CountryService) { }

  ngOnInit(): void {

    this.activatedRoute.params
      .pipe(
        // retornamos el observable de searchByAlphaCode()
        switchMap( (param) => this.countryService.searchByAlphaCode(param['id']) ),
        tap(console.log)
      )
      .subscribe(country => {
        this.country = country[0]
        console.log('country:', this.country)
      });

    // this.activatedRoute.params
    //   .subscribe(({id}) =>  {
    //     console.log(id);
    //     this.countryService.searchByAlphaCode(id)
    //       .subscribe(country => {
    //         console.log(country);
    //       })
    //   });
  }

}
