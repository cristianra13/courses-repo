import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FizzBuzzModel } from '../models/fizz-buzz.model';
import { FizzBuzzResponse } from '../models/fizz-buzz-response.model';
import { CustomHttpResponse } from '../models/custom-http-response.model';

@Injectable({
  providedIn: 'root'
})
export class FizzBuzzService {

  private url = 'http://localhost:8080/intraway/api/fizzbuzz';

  constructor(private http: HttpClient) { }

  registerFizzBuzz(fizzBuzz: FizzBuzzModel): Observable<FizzBuzzResponse> {
    return this.http.get<FizzBuzzResponse>(`${this.url}/${fizzBuzz.min}/${fizzBuzz.max}`);
  }

  listAllFizzBuzzRegisters(): Observable<FizzBuzzResponse[]>  {
    return this.http.get<FizzBuzzResponse[]>(`${this.url}/list`);
  }
}
