import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SearchGifsResponse, Gif } from '../interfaces/gifs.interface';

@Injectable({
  providedIn: 'root'
})
export class GifsService {

  private _history: string[] = [];
  private apiKey: string = '7KdRAwUvgTN7MLQzMVaLqBWGtyDdsTin';
  private serviceUrl = 'https://api.giphy.com/v1/gifs';

  public results: Gif[] = [];

  get history() {
    return [...this._history];
  }

  constructor(private http: HttpClient) {
    this._history = JSON.parse(localStorage.getItem('history')!) || [];
    this.results = JSON.parse(localStorage.getItem('results')!) || [];
    // if (localStorage.getItem('history')) {
    //   this._history = JSON.parse(localStorage.getItem('history')!) || [];
    // }
  }

  searchGifs(query: string) {
    query = query.trim().toLowerCase();
    if (!this._history.includes(query)) {
      this._history.unshift(query);
      this._history = this._history.splice(0, 10);
      localStorage.setItem('history', JSON.stringify(this._history));
    }

    const params = new HttpParams()
      .set('apiKey', this.apiKey)
      .set('limit', '10')
      .set('q', query);

    this.http.get<SearchGifsResponse>(`${this.serviceUrl}/search`, { params })
      .subscribe( response =>  {
        this.results = response.data;
        localStorage.setItem('results', JSON.stringify(this.results));
      });

  }

}
