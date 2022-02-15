import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { Game } from '../interfaces/interfaces';


@Injectable({
  providedIn: 'root'
})
export class GameService {

  private juegos: Game[] = [];

  constructor(private http: HttpClient) { }

  getNominados()
  {
    if (this.juegos.length > 0)
    {
      return of(this.juegos);
    }
    else {
      return this.http.get<Game[]>(`${ environment.url }/api/goty`)
          .pipe(
            tap(
              juegos => this.juegos = juegos
            )
          );
    }
  }

  votarJuego(id: string)
  {
    return this.http.post(`${ environment.url }/api/goty/${id}`, {})
    .pipe(
      catchError( (err: any) => {
        return of(err.error);
      })
    );
  }
}
