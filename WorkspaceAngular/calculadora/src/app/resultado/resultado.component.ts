import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-resultado',
  templateUrl: './resultado.component.html',
  styleUrls: ['./resultado.component.css']
})
export class ResultadoComponent implements OnInit
{
  // el resultado proviene de la clase padre
  @Input() resultado: number;

  constructor() { }

  ngOnInit(): void {
  }

}
