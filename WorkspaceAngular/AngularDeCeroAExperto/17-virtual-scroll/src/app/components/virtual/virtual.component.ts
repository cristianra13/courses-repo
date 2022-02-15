import { CdkVirtualScrollViewport } from '@angular/cdk/scrolling';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-virtual',
  templateUrl: './virtual.component.html',
  styleUrls: ['./virtual.component.css']
})
export class VirtualComponent implements OnInit {

  // elemento que va a seleccionar del HTML
  // Obtenemos una referencia a todo el objeto CdkVirtualScrollViewport con metodos y propiedades
  @ViewChild( CdkVirtualScrollViewport ) viewPort: CdkVirtualScrollViewport;

  personas = Array(1000).fill(0);

  constructor() { }

  ngOnInit(): void {
    console.log(this.personas);
  }

  irFinalScroll()
  {
    this.viewPort.scrollToIndex(this.personas.length);
  }

  irInicioScroll()
  {
    this.viewPort.scrollToIndex(this.personas[0]);
  }

  irMedioScroll()
  {
    this.viewPort.scrollToIndex(this.personas.length / 2);
  }

}
