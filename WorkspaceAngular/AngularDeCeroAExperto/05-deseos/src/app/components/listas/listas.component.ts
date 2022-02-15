import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { DeseosService } from '../../services/deseos.service';
import { Lista } from '../../models/lista.model';
import { Router } from '@angular/router';
import { AlertController, IonList } from '@ionic/angular';

@Component({
  selector: 'app-listas',
  templateUrl: './listas.component.html',
  styleUrls: ['./listas.component.scss'],
})
export class ListasComponent implements OnInit 
{
  // toma un elemento html
  @ViewChild(IonList) lista: IonList;
  @Input() terminada = true;

  constructor(public deseosService: DeseosService,
              private router: Router,
              private alertctrl: AlertController)
  {

  }

  ngOnInit() {}

  listaSeleccionada(lista: Lista)
  {
    if (this.terminada)
    {
      this.router.navigateByUrl(`/tabs/tab2/agregar/${lista.id}`); 
    }
    else
    {
      this.router.navigateByUrl(`/tabs/tab1/agregar/${lista.id}`);
    }
  }

  borrarLista(item: Lista)
  {
    this.deseosService.borrarLista(item);
  }

  async editarLista(lista: Lista)
  {
    const alert = this.alertctrl.create({
      header: 'Editar Lista',
      inputs: [
        {
          name: 'titulo', // este nombre debe ser el mismo del model, titulo
          type: 'text',
          value: lista.titulo,
          placeholder: 'Nombre de la lista'
        }
      ],
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: () => {
            console.log('Cancelar');
            this.lista.closeSlidingItems();
          }
        },
        {
          text: 'Actualizar',
          handler: (data) => {
            console.log(data);
            if ( data.titulo === '' ) {
              return;
            }
            lista.titulo = data.titulo;
            this.deseosService.guardarStorage();
            this.lista.closeSlidingItems();
          }
        }
      ]

    });

    (await alert).present();
  }
}
