import { Injectable } from '@angular/core';
import {PersonInterface} from '../interfaces/person.interface';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  people: PersonInterface[] = [];

  constructor() {
    console.log('PeopleService init ialized');
  }
}
