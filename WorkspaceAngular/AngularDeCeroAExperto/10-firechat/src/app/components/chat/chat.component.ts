import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../providers/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styles: [
  ]
})
export class ChatComponent implements OnInit
{
  mensaje: string = '';
  elemento: any;

  constructor(public chatService: ChatService) {
    this.chatService.cargarMensajes().subscribe( () =>
    {
      setTimeout(() => {
        this.elemento.scrollTop = this.elemento.scrollHeight;
      }, 20);

    } );
  }

  ngOnInit()
  {
    this.elemento = document.getElementById('app-mensajes');
  }

  enviarMensaje()
  {
    console.log(this.mensaje);

    if (this.mensaje.length === 0)
    {
      return;
    }

    this.chatService.agregarMensaje(this.mensaje)
      .then( () => {
        console.log('Ingreso then');

        this.mensaje = '';
      })
      .catch( (err) => console.error('Error al enviar', err)
    );

  }

}
