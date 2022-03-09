import { Component, OnInit } from '@angular/core';
import { CountryService } from '../../services/country.service';
import { Country } from '../../interfaces/country.interface';

@Component({
  selector: 'app-by-region',
  templateUrl: './by-region.component.html',
  styles: [
    `
      button {
        margin-right: 5px;
      }
    `
  ]
})
export class ByRegionComponent {

  regions: string[] = ['africa', 'americas', 'asia', 'europe', 'oceania'];
  countries: Country[] = [];
  activedRegion: string = '';

  constructor(private countryService: CountryService) { }

  getCSSClass(region: string) {
    return (region === this.activedRegion ? 'btn btn-primary' : 'btn btn-outline-primary');
  }

  activeRegion(region: string) {
    if (region === this.activedRegion) {
      return;
    }
    this.activedRegion = region;
    this.countries = [];
    this.countryService.searchByRegion(region)
      .subscribe(countries =>  {
        console.log(countries);
        this.countries = countries.sort((a, b) => a.name.common.localeCompare(b.name.common));
      });
  }

}
