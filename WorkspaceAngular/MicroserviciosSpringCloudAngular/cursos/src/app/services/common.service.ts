import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Generic } from '../models/generic';

export abstract class CommonService<E extends Generic> {

  protected baseEndpoint: string;
  protected headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(protected client: HttpClient) {
  }

  listar(): Observable<E[]>{
    return this.client.get<E[]>(this.baseEndpoint);
  }

  listarPaginado(page: string, size: string): Observable<any>{
    const params: HttpParams = new HttpParams()
      .set('page', page)
      .set('size', size);

    return this.client.get<any>(`${this.baseEndpoint}/pagina`, {params: params});
  }

  detalle(id: number): Observable<E>{
    return this.client.get<E>(`${this.baseEndpoint}/${id}`);
  }

  crear(e: E): Observable<E>{
    return this.client.post<E>(this.baseEndpoint, e, {headers: this.headers});
  }

  editar(e: E): Observable<E>{
    return this.client.put<E>(`${this.baseEndpoint}/${e.id}`, e, {headers: this.headers});
  }

  eliminar(id: number): Observable<void>{
    return this.client.delete<void>(`${this.baseEndpoint}/${id}`);
  }

}
