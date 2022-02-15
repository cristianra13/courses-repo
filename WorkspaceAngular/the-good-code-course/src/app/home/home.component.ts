import {Component, EventEmitter, OnInit} from '@angular/core';
import {PeopleService} from '../services/people.service';
import {PersonInterface} from '../interfaces/person.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  blueTitle = 'Hello Blue 🟦';
  greenTitle = 'Hello Green  🟩';
  redTitle = 'Hello Red 🟥';

  blueClicks: number = 0;
  greenClicks: number = 0;
  redClicks: number = 0;
  people: PersonInterface[] = [];

  message = 'Esto es un mensaje';

  constructor(private peopleService: PeopleService) {
    this.people = peopleService.people;
  }

  ngOnInit(): void {
    // Le pasamos 1000 ms que son 1 segundo
    // this.recursivePrint(1000);
  }

// Metodos escuchadores de eventos desde clases hijas
  // en esta clase recibimos desde la clase hija clicks
  onBlueClicks(clicks: number){
    this.blueClicks = clicks;
  }

  onGreenClicks(clicks: number){
    this.greenClicks = clicks;
  }

  /*onRedClicks(clicks: number){
    this.redClicks = clicks;
  }*/

  reset(){
    this.blueClicks = 0;
    this.greenClicks = 0;
    this.redClicks = 0;

    this.blueTitle = 'Hola desde <app-blue>' + Math.floor(Math.random() * 101);
    this.greenTitle = 'Hola desde <app-green> 💚';
    this.redTitle = 'Hola desde <app-red>' + Math.floor(Math.random() * 101);
  }

  // metodo recursivo - se llama así mismo
  // Este se va a llamar así mismo cada segundo
  private recursivePrint(ms: number){
    setTimeout(() => {
      console.log(this.message);
      this.recursivePrint(ms);
    }, ms); // en esta parte se pasa el tiempo al cual cada cierto debe ejecutarse
  }
}
