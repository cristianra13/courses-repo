import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import * as firebase from 'firebase';
import { FileItem } from '../models/file-item.model';


@Injectable({
  providedIn: 'root'
})
export class CargaImagenesService
{

  private CARPETA_IMAGENES = 'img';

  constructor(private db: AngularFirestore) { }

  cargarImagenesFirebase(imagenes: FileItem[])
  {
    //tomamos la referencia del storage
    const storageRef = firebase.storage().ref();

    for (const item of imagenes)
    {
      item.estaSubiendo = true;
      if (item.progreso >= 100)
      {
        continue;
      }

      const uploadTask: firebase.storage.UploadTask = storageRef.child(`${this.CARPETA_IMAGENES}/${item.nombreArchivo}`).put(item.archivo);

      // se dispara esto cada vez que el estado camba
      uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
          ( snapshot: firebase.storage.UploadTaskSnapshot ) => item.progreso = ( snapshot.bytesTransferred / snapshot.totalBytes ) * 100,
          ( error ) => console.error('Error al subir ', error),
          (  ) => {
            /* console.log('Imagen cargada correctamente');
            item.url = uploadTask.snapshot.getDow;
            item.estaSubiendo = false;
            this.guardarImagen({
              nombre: item.nombreArchivo,
              url: item.url
            }); */

            uploadTask.snapshot.ref.getDownloadURL()
            .then((url) => {
              item.url = url;
              item.estaSubiendo = false;
              this.guardarImagen({
                nombre: item.nombreArchivo,
                url: item.url
              });
            });

          }
        )
    }
  }

  private guardarImagen(imagen: {nombre: string, url: string})
  {
    this.db.collection(`/${this.CARPETA_IMAGENES}`).add( imagen );
  }
}
