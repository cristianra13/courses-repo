import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { YoutubeResponse } from '../models/youtube.model';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class YoutubeService
{

  private youtubeUrl = 'https://www.googleapis.com/youtube/v3';
  private apiKey = 'AIzaSyBeYSgYl57w9W0a_FIUqjiHlkkXE99HYWw';
  private playlist = 'UUuaPTYj15JSkETGnEseaFFg';
  private nextpageToken = '';

  constructor(private http: HttpClient)
  {
  }

  getVideos()
  {
    const url = `${this.youtubeUrl}/playlistItems`;

    const params = new HttpParams()
      .set('part', 'snippet')
      .set('maxResults', '10')
      .set('playlistId', this.playlist)
      .set('key', this.apiKey)
      .set('pageToken', this.nextpageToken);

    return this.http.get<YoutubeResponse>(url, { params })
      .pipe( map( resp => {
        this.nextpageToken = resp.nextPageToken;
        return resp.items;
      }),

      map( items => items.map( video => video.snippet )));
  }
}
