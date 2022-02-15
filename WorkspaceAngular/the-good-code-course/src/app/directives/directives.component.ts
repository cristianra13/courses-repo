import { Component, OnInit } from '@angular/core';
import {PersonInterface} from '../interfaces/person.interface';
import {PeopleService} from '../services/people.service';

@Component({
  selector: 'app-directives',
  templateUrl: './directives.component.html',
  styleUrls: ['./directives.component.css']
})
export class DirectivesComponent implements OnInit {

  text: string;
  names: string[] = [];
  people: PersonInterface[] = [];
  lastId = 0;

  constructor(private peopeService: PeopleService) {
    this.people = this.peopeService.people;
  }

  ngOnInit(): void {
  }

  save(){
    if (this.text.length){
      this.names.push(this.text);
      this.text = '';
    }
  }

  savePerson(){
    if (this.text.length){
      const person: PersonInterface = {
        id: ++this.lastId,
        icon: '😂',
        name: this.text
      };
      this.people.push(person);
      this.text = '';
    }
  }

  removeAll(){
    this.peopeService.people.length = 0;
  }

}
