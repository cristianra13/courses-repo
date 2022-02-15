import { Directive, EventEmitter, ElementRef, HostListener, Input, Output } from '@angular/core';
import { FileItem } from '../models/file-item.model';

@Directive({
  selector: '[appNgDropFiles]'
})
export class NgDropFilesDirective
{

  @Input() archivos: FileItem[] = [];
  // en el EventEmmiter vamos a emitir un booleano
  @Output() mouseSobre: EventEmitter<boolean> = new EventEmitter();

  constructor() { }

  // callback cuando suceda el dragover, dispara un evento ['$event']
  @HostListener('dragover', ['$event'])
  public onDragEnter(evento: any)
  {
     // disparamos notificación para qe el padre sepa que está encima del drop
     this.mouseSobre.emit(true); // el mouse se encuentra sobre el elemento
     this._prevenirDetener(evento);
  }

  @HostListener('dragleave', ['$event'])
  public onDragLeave(evento: any)
  {
     // disparamos notificación para qe el padre sepa que está encima del drop
     this.mouseSobre.emit(false); // el mouse se encuentra sobre el elemento
     // this._prevenirDetener(evento);
  }

  // cuando se arrastra algo y se suelta dragdrop
  @HostListener('drop', ['$event'])
  public onDrop(evento: any)
  {
    const transferencia = this._getTransferencia(evento);

    if (!transferencia)
    {
      return;
    }

    this._extraerArchivos(transferencia.files);
    this._prevenirDetener(evento);

    // disparamos notificación para qe el padre sepa que está encima del drop
    this.mouseSobre.emit(false); // el mouse se encuentra sobre el elemento

  }

  // nos ayuda con compatibilidad
  private _getTransferencia(event: any)
  {
    // esto es porque hay navegadores que interpretan el drag and drop de manera diferente
    return event.dataTransfer ? event.dataTransfer : event.originalEvent.dataTransfer;
  }

  // dentro de FileList, se encuentran todos lod objetos a los que se les hizo drag and drop
  private _extraerArchivos(archivosLista: FileList)
  {
    console.log(archivosLista);

    // tslint:disable-next-line: forin
    for ( const propiedad in Object.getOwnPropertyNames(archivosLista) )
    {
      const archivoTemporal = archivosLista[propiedad];
      if (this._archivoPuedeSercargado(archivoTemporal))
      {
        const nuevoArchivo = new FileItem(archivoTemporal);
        this.archivos.push(nuevoArchivo)
      }
    }
    console.log(this.archivos);


  }

  // validaciones

  private _archivoPuedeSercargado(archivo: File): boolean
  {
    // si el archivo no ha sido cargado y es una imagen
    if (!this._archivoYaDropeado(archivo.name) && this.esImagen(archivo.type))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  private _prevenirDetener(event)
  {
    event.preventDefault();
    event.stopPropagation();
  }

  // validar que el archivo no exista en el arreglo de archivos
  private _archivoYaDropeado(nombreArchivo: string): boolean
  {
    for (const archivo of this.archivos)
    {
      if (archivo.nombreArchivo == nombreArchivo)
      {
        console.log('Archivo ' + nombreArchivo + ' ya se agrego');
        return true;
      }
    }
    return false;
  }

  // validación de solo imagenes
  private esImagen(tipoArchivo: string): boolean
  {
     return (tipoArchivo === '' || tipoArchivo === undefined) ? false : tipoArchivo.startsWith('image');
  }

}
