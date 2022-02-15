import dotenv from 'dotenv';
dotenv.config();

import express, { Application } from "express";
// alias a la exportación por defecto
import userRoutes from '../routes/user.routes';
import cors from "cors";
import db from '../databse/connection';

class Server {
    
    private app: express.Application;
    private port: string;
    
    private apiPaths = {
        userPaths: '/api/users'
    }

    constructor() {
        this.app = express();
        this.port = process.env.PORT || '3000';

        // conexión a db
        this.dbConnection();
        // middlewares, métodos iniciales
        this.middlewares();
        // definimos mis rutas
        this.routes();
    }

    // conectar Base de datos
    async dbConnection() {
        try {
            
            await db.authenticate();
            console.log('Db Online');

        } catch (error) {
            throw new Error(error);
        }
    }

    // funciones que se ejecutan antes de que pasen nuestras rutas o se ejecutan antes de otros procdimientos
    middlewares() {

        // CORS
        this.app.use(cors());

        // Lectura body y parseo de body
        this.app.use(express.json());

        // Carpeta publica
        this.app.use(express.static('public'));
    }

    routes() {
        this.app.use(this.apiPaths.userPaths, userRoutes);
    }

    listen() {
        this.app.listen(this.port, () => {
            console.log('Server running on port', this.port);
        });
    }
}

// clase que se exporta por defecto
export default Server;