const fs = require('fs');

class Ticket {

    constructor(numeroTicket, escritorio) {

        this.numeroTicket = numeroTicket;
        this.escritorio = escritorio;
    }
}


class TicketControl {

    constructor() {

        // definimos propiedades
        this.ultimoTicket = 0;
        this.hoy = new Date().getDate();
        this.tickets = []; // contiene todos los tickets pendientes que no se han atendido
        this.ultimosCuatro = [];

        // leemos la data del archivo json
        let data = require('../data/data.json');


        // si la fecha del archivo json es igual al día de hoy
        if (data.hoy === this.hoy) {
            this.ultimoTicket = data.ultimoTicket;
            this.tickets = data.tickets;
            this.ultimosCuatro = data.ultimosCuatro;
        } else {
            this.reiniciarConteo();
        }
    }


    /**
     * Asigna el siguiente ticket
     */
    siguienteTicket() {

        this.ultimoTicket += 1;

        let ticket = new Ticket(this.ultimoTicket, null);
        this.tickets.push(ticket);

        this.grabarData();

        return `Ticket ${this.ultimoTicket}`;
    }

    getUltimoTicket() {

        return `Ticket ${this.ultimoTicket}`;
    }

    getUltimosCuatro() {

        return this.ultimosCuatro;
    }

    /**
     * funcion para atender los tickets en los escritorios
     */
    atenderTicket(escritorio) {

        // verificamos que hayan tickets pendientes de atender
        if (this.tickets.length === 0) {
            return 'No hay tickets pendientes';
        }

        let numeroTicket = this.tickets[0].numeroTicket;
        // eliminamos el primer elemento del arreglo el cual tomamos en la linea anterior
        this.tickets.shift();

        let atenderTicket = new Ticket(numeroTicket, escritorio);

        // Agregamos el ticket al inicio del arreglo
        this.ultimosCuatro.unshift(atenderTicket);

        if (this.ultimosCuatro.length > 4) {
            // borra el ultimo elemento
            this.ultimosCuatro.splice(-1, 1);
        }

        console.log('ultimos 4: ', this.ultimosCuatro);

        this.grabarData();

        return atenderTicket;
    }

    /**
     * reinicia el conteo de los tickets
     */
    reiniciarConteo() {

        this.ultimoTicket = 0;
        this.tickets = [];
        this.ultimosCuatro = [];
        this.grabarData();
    }

    /**
     * Graba la data en el archivo json
     */
    grabarData() {

        let jsonData = {
            ultimoTicket: this.ultimoTicket,
            hoy: this.hoy,
            tickets: this.tickets,
            ultimosCuatro: this.ultimosCuatro
        };

        let jsonDataString = JSON.stringify(jsonData);

        fs.writeFileSync('./server/data/data.json', jsonDataString);
    }



}

module.exports = {
    TicketControl
}