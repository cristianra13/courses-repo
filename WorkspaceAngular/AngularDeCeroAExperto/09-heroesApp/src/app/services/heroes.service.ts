import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HeroeModel } from '../models/heroe.model';
import { map, delay } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class HeroesService {

  url = 'https://login-app-1a341.firebaseio.com';

  constructor(private http: HttpClient)
  {

  }


  crearHeroe(heroe: HeroeModel)
  {
    return this.http.post(`${this.url}/heroes.json`, heroe).pipe(
      map( (response: any) => {
        heroe.id = response.name;
        return heroe;
      })
    );
  }

  actualizarHeroe(heroe: HeroeModel)
  {
    const heroeTemp = {
      ...heroe // Se encarga de tomar cada una de las propiedades del heroe y crear una nueva con el mismo nombre
    };
    delete heroeTemp.id; // eliminamos el id del heroe para no enviarlo a firebase
    return this.http.put(`${this.url}/heroes/${heroe.id}.json`, heroeTemp);
  }

  borrarHeroe(id: string)
  {
    return this.http.delete(`${this.url}/heroes/${id}.json`);
  }

  getHeroe(id: string)
  {
    return this.http.get(`${this.url}/heroes/${id}.json`);
  }

  getHeroes()
  {
    /**
     * El map transforma la información y regresa cualquier otra cosa
     */
    return this.http.get(`${this.url}/heroes.json`).pipe( map( this.crearArreglo ), delay(0));
  }

  private crearArreglo(heroesObj: object)
  {
    const heroes: HeroeModel[] = [];

    if (heroesObj === null) { return []; } //por si no devuelve nada firebase

    Object.keys( heroesObj ).forEach( key => {
      const heroe: HeroeModel = heroesObj[key];
      heroe.id = key;
      heroes.push(heroe);
    });

    return heroes;
  }
}
