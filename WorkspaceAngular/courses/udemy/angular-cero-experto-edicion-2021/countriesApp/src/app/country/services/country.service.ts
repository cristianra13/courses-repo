import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { Country } from '../interfaces/country.interface';


@Injectable({
  providedIn: 'root'
})
export class CountryService {

  private apiUrl: string = 'https://restcountries.com/v3.1';
  get httpParams () {
    return new HttpParams()
      .set('fields', 'name,capital,cca2,flags,population');
  }

  constructor(private http: HttpClient) {}

  searchCountry(searchterm: string): Observable<Country[]> {
    const url = `${this.apiUrl}/name/${searchterm}`
    return this.http.get<Country[]>(url, {params: this.httpParams});
  }

  searchByCapitalCity(searchterm: string): Observable<Country[]> {
    const url = `${this.apiUrl}/capital/${searchterm}`;
    return this.http.get<Country[]>(url, {params: this.httpParams});
  }

  searchByAlphaCode(code: string): Observable<Country> {
    const url = `${this.apiUrl}/alpha/${code}`;
    return this.http.get<Country>(url);
  }

  searchByRegion(region: string): Observable<Country[]> {
    const url = `${this.apiUrl}/region/${region}`;
    return this.http.get<Country[]>(url, { params: this.httpParams });
  }

}
