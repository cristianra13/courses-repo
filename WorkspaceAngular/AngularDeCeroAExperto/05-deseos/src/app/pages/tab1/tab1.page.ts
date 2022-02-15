import { Component } from '@angular/core';
import { DeseosService } from '../../services/deseos.service';
import { Lista } from '../../models/lista.model';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page 
{
  items: Lista[];

  constructor(public deseosService: DeseosService, 
              private router: Router,
              private alertCtrl: AlertController) 
  {
    this.items = deseosService.listas;
  }


  // async transforma toda la función a una promesa
  async agregarLista()
  {
    const alert = this.alertCtrl.create({
      header: 'Nueva lista',
      inputs: [
        {
          name: 'titulo', // este nombre debe ser el mismo del model, titulo
          type: 'text',
          placeholder: 'Nombre de la lista'
        }
      ],
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: () => {
            console.log('Cancelar');
          }
        },
        {
          text: 'Crear',
          handler: ( data ) => {
            console.log(data);
            if ( data.titulo === '' ) {
              return;
            }
            const listaId = this.deseosService.crearLista( data.titulo );
            // Tengo que crear la lista
            
            this.router.navigateByUrl(`/tabs/tab1/agregar/${listaId}`);
          }
        }
      ]
    });

    (await alert).present();
  }
}
