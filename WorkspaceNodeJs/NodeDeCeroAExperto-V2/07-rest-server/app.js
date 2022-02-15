// Imports
require('dotenv').config();

const Server = require('./models/server');


const server = new Server();

// launch listen
server.listen();