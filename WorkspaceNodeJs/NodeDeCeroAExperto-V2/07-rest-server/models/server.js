const express = require('express');
const cors = require('cors');
const { dbConnection } = require('../database/config.db');
const fileUpload = require('express-fileupload');


class Server {

    constructor() {
        this.app = express();
        this.port = process.env.PORT;
        this.paths = {
            authPath: '/api/auth',
            buscarPath: '/api/buscar',
            usersPath: '/api/users',
            categoriasPath: '/api/categorias',
            productosPath: '/api/productos',
            uploadsPath: '/api/uploads'
        }

        // connect to DB
        this.databaseConnect();

        // middlewares
        this.middlewares();

        // rutas de aplicación
        this.routes();
    }

    async databaseConnect() {
        await dbConnection();
    }


    routes() {
        // este es el path que ocupamos para ir hasta los endpoints de users
        this.app.use(this.paths.authPath, require('../routes/auth-routes'));
        this.app.use(this.paths.buscarPath, require('../routes/buscar-routes'));
        this.app.use(this.paths.usersPath, require('../routes/user-routes'));
        this.app.use(this.paths.categoriasPath, require('../routes/categorias-routes'));
        this.app.use(this.paths.productosPath, require('../routes/productos-routes'));
        this.app.use(this.paths.uploadsPath, require('../routes/uploads-routes'));
    }


    /**
     * Direcotiroo publico
     */

    middlewares() {
        // CORS
        this.app.use(cors());

        // lectura y parseo de body, cualquier información que venga, se va a serializar a json
        this.app.use(express.json());

        this.app.use(express.static('public'));

        // Fileupload o carga de archivos
        this.app.use(fileUpload({
            useTempFiles: true,
            tempFileDir: '/tmp/',
            createParentPath: true
        }));
    }

    listen() {
        this.app.listen(this.port, console.log(`Server running on port ${this.port}`));

    }


}

module.exports = Server;