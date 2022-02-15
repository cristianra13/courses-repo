import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appResaltado]'
})
export class ResaltadoDirective {

  constructor(private el: ElementRef)
  {
    console.log('Directiva llamada');
  }

  // le decimos que esta variable viene de afuera
  @Input('appResaltado') nuevoColor: string;

  // evento que va a estar escuchando, mouseEntro es el alias
  @HostListener('mouseenter') mouseEntro()
  {
    this.resaltar(this.nuevoColor || 'yellow');
  }

  @HostListener('mouseleave') mouseSalio()
  {
    this.resaltar(null);
  }

  private resaltar(color: string)
  {
    this.el.nativeElement.style.backgroundColor = color;
  }

}
