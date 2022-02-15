const http = require('http');


http.createServer((req, res) => {
        res.write('Hola mundo');
        res.end();
    })
    .listen(3000);

console.log('Listening port 3000');