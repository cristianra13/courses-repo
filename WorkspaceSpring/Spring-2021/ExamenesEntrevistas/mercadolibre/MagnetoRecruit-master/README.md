# Examen Mercadolibre
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Mens.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

## boolean isMutant(String[] dna);

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

![enter image description here](https://fotos.subefotos.com/861243f53091dc5758f70d9e73b3ec44o.jpg)

## No-Mutante Mutante

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.
Ejemplo (Caso mutante):
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
En este caso el llamado a la función isMutant(dna) devuelve “true”.
Desarrolla el algoritmo de la manera más eficiente posible.

## Desafíos:
### Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.
### Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine,
Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es
mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el
siguiente formato:
POST → /mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden


# Resolución

### Nivel 1:
Se propone implementar como solución agrupar de 4 caracteres y preguntar si son iguales, por medio de la función isEqual(char a, char b, char c, char d). Para evitar utilizar matrices y reducir espacio en memoria, se recorre Dna por medio de dos for, donde “i” determina la posición dentro del array y “j” posiciones dentro del string mediante el método charAt.

A partir de una posición se analiza fila, columna, diagonal y diagonal invertida, para la misma letra inicial.

Además se valida Dna recibido, para que las cadenas de ADN sean mayores o iguales a 4, que la longitud del String sea igual al tamaño del Array (NxN) y las letras permitidas A, T, C,  y G.

#### Tecnología Utilizada
- Netbeans IDE 8.2
- Java 7
- Jdk 1.8
- Maven
- Tomcat 8
- Google Cloud

### Nivel 2:

#### Url Api Google Cloud Apache Tomcat/8.5.14 (Debian)
http://35.223.43.127/MagnetoRecruit/mutant/isMutant

##### Metodo Get

Estado HTTP 501 – Not Implemented


##### Metodo Post

Content-Type: application/json

Request Mutante Permitido
```json
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
Response Status: 200 OK

Request Humano Permitido
```json
{
    "dna": [
        "TTGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCTCTA",
        "TCACTG"
    ]
}
```
Response Status: 403 Forbidden

Request Rechazado
```json
{
    "dna": [
        "TTHCGA",
        "CAG1GC",
        "TTAGT",
        "AGAAGG",
        "CCTCTA",
        "TCACTG"
    ]
}
```
Response Status: 400 Bad Request


Request Rechazado (Error Json)
```json
{
    "dnaa": [
        "TTHCGA",
        "CAG1GC",
        "TTAGT",
        "AGAAGG",
        "CCTCTA",
        "TCACTG"
    ]
}
```
Response Status: 400 Bad Request

