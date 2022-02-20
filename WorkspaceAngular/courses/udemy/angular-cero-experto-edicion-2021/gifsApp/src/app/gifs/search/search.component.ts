import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { GifsService } from '../services/gifs.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styles: [
  ]
})
export class SearchComponent {

  // Dentro del parentesis el nombre dle elemento a buscar en el componente HTML -> txtSearch
  // -> ! non null assertion operator
  @ViewChild('txtSearch') txtSearch!: ElementRef<HTMLInputElement>;

  constructor(private gifsService: GifsService) {

  }

  search() {
    const value = this.txtSearch.nativeElement.value;
    if (value.trim().length == 0) {
      return;
    }
    this.gifsService.searchGifs(value);
    this.txtSearch.nativeElement.value = '';
  }
}
