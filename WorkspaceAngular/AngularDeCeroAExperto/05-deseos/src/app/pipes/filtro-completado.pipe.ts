import { Pipe, PipeTransform } from '@angular/core';
import { Lista } from '../models/lista.model';

@Pipe({
  name: 'filtroCompletado',
  /**
   * quiere decir que cada vez que se detecte el ciclo de deteccion de 
   * cambios, siempre se va a llamar
   *  */ 
  pure: false
})
export class FiltroCompletadoPipe implements PipeTransform {

  transform(listas: Lista[], completada: boolean = true): Lista[]
  {
    return listas.filter( lista => lista.terminada === completada);
  }

}
