import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {

  heroes: string[] = ['Ironman', 'Spiderman', 'Hulk', 'Thor', 'Black Widow'];
  deletedHeroes: string[] = [];
  deletedHero = '';

  constructor() { }

  ngOnInit(): void {
  }

  deleteHero() {
    this.deletedHero = this.heroes.shift() || '';
    this.deletedHeroes.push(this.deletedHero);
  }

}
