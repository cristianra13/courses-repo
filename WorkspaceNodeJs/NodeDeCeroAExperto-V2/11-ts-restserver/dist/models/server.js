"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const dotenv_1 = __importDefault(require("dotenv"));
dotenv_1.default.config();
const express_1 = __importDefault(require("express"));
// alias a la exportación por defecto
const user_routes_1 = __importDefault(require("../routes/user.routes"));
const cors_1 = __importDefault(require("cors"));
const connection_1 = __importDefault(require("../databse/connection"));
class Server {
    constructor() {
        this.apiPaths = {
            userPaths: '/api/users'
        };
        this.app = express_1.default();
        this.port = process.env.PORT || '3000';
        // conexión a db
        this.dbConnection();
        // middlewares, métodos iniciales
        this.middlewares();
        // definimos mis rutas
        this.routes();
    }
    // conectar Base de datos
    dbConnection() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                yield connection_1.default.authenticate();
                console.log('Db Online');
            }
            catch (error) {
                throw new Error(error);
            }
        });
    }
    // funciones que se ejecutan antes de que pasen nuestras rutas o se ejecutan antes de otros procdimientos
    middlewares() {
        // CORS
        this.app.use(cors_1.default());
        // Lectura body y parseo de body
        this.app.use(express_1.default.json());
        // Carpeta publica
        this.app.use(express_1.default.static('public'));
    }
    routes() {
        this.app.use(this.apiPaths.userPaths, user_routes_1.default);
    }
    listen() {
        this.app.listen(this.port, () => {
            console.log('Server running on port', this.port);
        });
    }
}
// clase que se exporta por defecto
exports.default = Server;
//# sourceMappingURL=server.js.map