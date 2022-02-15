const http = require('http');

// Para escuchar peticiones http primero creamos el servidor

http.createServer((request, response) => {

        response.writeHead(200, { 'Content-type': 'application/json' })

        let salida = {
            nombre: 'Cristian',
            edad: 27,
            pais: 'Colombia',
            url: request.url
        }

        response.write(JSON.stringify(salida))
        response.end()
    })
    .listen(8080)

console.log('Escuchando el puerto 8080');