const express = require('express');
const cors = require('cors');
const { socketController } = require('../sockets/controller-communication');



class Server {

    constructor() {
        this.app = express();
        this.port = process.env.PORT;
        this.server = require('http').createServer(this.app);
        // io maneja la información de los usuarios conectados
        this.io = require('socket.io')(this.server);

        this.paths = {}
            // middlewares
        this.middlewares();

        // Rutas de la aplicación
        this.routes();

        // Configuración de sockets
        this.sockets();
    }

    routes() {
        // este es el path que ocupamos para ir hasta los endpoints de users
        // this.app.use(this.paths.authPath, require('../routes/auth-routes'));
    }

    /**
     * Direcotiroo publico
     */

    middlewares() {
        // CORS
        this.app.use(cors());
        // servimos la carpeta publica como recurso estatico
        this.app.use(express.static('public'));
    }

    listen() {
        this.server.listen(this.port, console.log(`Server running on port ${this.port}`));

    }

    sockets() {
        this.io.on('connection', socketController);
    }


}


module.exports = Server;