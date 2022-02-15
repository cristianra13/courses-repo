import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// Observabe
import { map } from 'rxjs/operators';

@Injectable({
  // Con este provideIn no es necesario agregar el servicio a providers en app.module.ts
  providedIn: 'root'
})
export class SpotifyService
{
  constructor(private http: HttpClient)
  {

  }

  getQuery(query: string)
  {
    const url = `https://api.spotify.com/v1/${ query }`;

    const headers = new HttpHeaders({
      'Authorization': 'Bearer BQDZq2d7FluXSoHqeZL9akqkzVcoZmiqPtrKCsSBhtz7sBaFOd_IWx1vBGd4Eb0nR_JBngkMjTp6GHVJZ5k'
    });

    // Se retorna un observable
    return this.http.get(url, { headers });
  }

  getNewreleases()
  {
    // Retornamos de la respuesta de Spotify la respuesta de albums.items
    return this.getQuery('browse/new-releases').pipe( map( data => data['albums'].items));
  }

  getArtistas(termino: string)
  {
    return this.getQuery(`search?q=${termino}&type=artist&limit=15`).pipe( map( data => data['artists'].items ));
  }

  getArtista(id: string)
  {
    return this.getQuery(`artists/${id}`);//.pipe( map( data => data['artists'].items ));
  }

  getTopTracks(id: string)
  {
    return this.getQuery(`artists/${id}/top-tracks?country=us`).pipe( map( data => data['tracks'] ));
  }


}
