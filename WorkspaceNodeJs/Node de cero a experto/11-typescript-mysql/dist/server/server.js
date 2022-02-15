"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express = require("express");
const path = require("path");
class Server {
    /**
     * @param port
     */
    constructor(port) {
        this.port = 3000;
        this.port = port;
        this.app = express();
    }
    /**
     * @param port
     */
    static init(port) {
        return new Server(port);
    }
    publicFolder() {
        const publicPath = path.resolve(__dirname, '../public');
        this.app.use(express.static(publicPath));
    }
    /**
     * @param callback
     */
    start(callback) {
        this.app.listen(this.port, callback);
        this.publicFolder();
    }
}
exports.default = Server;
