import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'noimage'
})
export class NoimagePipe implements PipeTransform {

  // recibimos un are¿reglo de las posibles imagenes que lleguen
  transform(images: any[]): string
  {
    if (!images)
    {
      /**
       * No se agrega ../../..... en la ruta de la imagen ya que se la aplicación corre
       * desde el path index.html por lo que este se encuentra en la raiz del proyecto
       */
      return 'assets/img/noimage.png';
    }

    if (images.length > 0)
    {
      return images[0].url;
    }
    else
    {
      return 'assets/img/noimage.png';
    }

    return null;
  }

}
