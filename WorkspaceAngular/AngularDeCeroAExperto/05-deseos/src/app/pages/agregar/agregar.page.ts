import { Component, OnInit } from '@angular/core';
import { DeseosService } from '../../services/deseos.service';
import { Route } from '@angular/compiler/src/core';
import { ActivatedRoute } from '@angular/router';
import { Lista } from '../../models/lista.model';
import { ListaItem } from '../../models/lista-item.model';

@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.page.html',
  styleUrls: ['./agregar.page.scss'],
})
export class AgregarPage implements OnInit 
{

  lista: Lista;
  nombreItem = '';

  constructor(private deseosService: DeseosService,
              private route: ActivatedRoute)
  {
    // leemos el id que se pasa por url, obetenemos el párametro con snapshot para no generar un observable
    const listaId = this.route.snapshot.paramMap.get('listaId');
    this.lista = deseosService.obtenerLista(listaId);
  }

  ngOnInit() 
  {
  }

  agregarItem()
  {
    if (this.nombreItem.length === 0)
    {
      return; // quiere decir que no se ingreso nada en el input
    }
    else
    {
      const nuevoItem = new ListaItem(this.nombreItem);
      this.lista.items.push(nuevoItem);

      this.nombreItem = '';
      this.deseosService.guardarStorage();
    }
  }

  cambioCheck(item: ListaItem)
  {
    console.log(item);
    // Filter regresa una colección de elementos que cumplan cierta condicion
    const pendientes = this.lista.items.filter(itemData => !itemData.completado).length;
    console.log({pendientes});

    if (pendientes === 0)
    {
      this.lista.terminadaEn = new Date();
      this.lista.terminada = true;
    }
    else
    {
      this.lista.terminadaEn = null;
      this.lista.terminada = false;
    }
    

    this.deseosService.guardarStorage();
  }

  borrar(id: number)
  {
    this.lista.items.splice(id, 1);
    this.deseosService.guardarStorage();
  }
}
