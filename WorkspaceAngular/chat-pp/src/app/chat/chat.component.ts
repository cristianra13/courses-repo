import { Component, OnInit } from '@angular/core';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {Mensaje} from './models/mensaje';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  private client: Client;
  conectado = false;
  mensaje: Mensaje = new Mensaje();
  mensajes: Mensaje[] = [];
  escribiendo: string;
  clienteId: string;

  constructor() {
    // se crea id para el cliente donde se agrega la fecha en milisegundos, un random de la clase Math de js
    this.clienteId = 'id-' + new Date().getTime() + '-' + Math.random().toString(36).substr(2);
  }

  ngOnInit() {
    this.client = new Client();
    this.client.webSocketFactory = () => {
      return new SockJS('http://localhost:8080/chat-websocket');
    };

    /*
    Objeto frame contiene toda la información de nuestra conexión con el objeto broker
     */
    this.client.onConnect = (frame) => {
      console.log('Conected: ' + this.client.connected + ' : ' + frame);
      this.conectado = true;

      // recibimos el mensaje
      this.client.subscribe('/chat/mensaje', e => {
        const mensaje: Mensaje = JSON.parse(e.body) as Mensaje;
        mensaje.fecha = new Date(mensaje.fecha);

        // tenemos que asignar el color solo al cliente que se conecto y no a todos
        if (!this.mensaje.color && mensaje.tipoMensaje === 'NUEVO_USUARIO' && this.mensaje.username === mensaje.username) {
          // Asignamos el mensaje del color que llega desde el broker (servidor)
          this.mensaje.color = mensaje.color;
        }
        this.mensajes.push(mensaje);
        console.log(mensaje);
      });

      // nos suscribimos al evento del historial
      this.client.subscribe('/chat/historial/' + this.clienteId, e => {
        const historial = JSON.parse(e.body) as Mensaje[];
        /*
        Se asigna el historial a mensajes
        se pasa la fecha a date ya que esta viene como un long desde el server

        Con reverse() damos vuelta a lo que recibimos ya que debemos mostrar del mas nuevo al ultimo
         */
        this.mensajes = historial.map(m => {
          m.fecha = new Date(m.fecha);
          return m;
        }).reverse();
      });

      // debemos notificar al broker que debemos recibir el historial de mensajes
      // debemos enviar el clienteId al broker
      this.client.publish({destination: '/app/historial', body: this.clienteId});

      this.client.subscribe('/chat/escribiendo', e => {
        this.escribiendo = e.body;
        // Se usa el metodo para mostrar que alguien esta escribiendo por algunos segundos
        // este metodo ejecuta una funcion callback por determinado tiempo
        setTimeout(() => this.escribiendo = '', 30000);
      });

      this.mensaje.tipoMensaje = 'NUEVO_USUARIO';
      this.client.publish({destination: '/app/mensaje', body: JSON.stringify(this.mensaje)});
    };

    this.client.onDisconnect = (frame) => {
      console.log('Disconected: ' + !this.client.connected + ' : ' + frame);
      this.conectado = false;
      this.mensajes = [];
      this.mensaje = new Mensaje();
    };
  }

  conectar(): void {
    // Metodo para conectarnos
    this.client.activate();
  }

  desconectar(): void {
    // Metodo para desconectarnos
    this.client.deactivate();
  }

  enviarMensaje(): void {
    this.mensaje.tipoMensaje = 'MENSAJE';
    // Como es un objeto que se envía en publish, va con llaves
    // Se agrega el prefijo de la clase config de spring
    this.client.publish({destination: '/app/mensaje', body: JSON.stringify(this.mensaje)});
    this.mensaje.texto = '';
  }

  escribiendoEvento(): void {
    // el prefijo /app/escribiendo es el que se tiene en el controller de Spring
    this.client.publish({destination: '/app/escribiendo', body: this.mensaje.username});
  }

}
