# Ejercicio Mutantes - Mercadolibre

Examen Técnico para MercadoLibre. 

Introducción:
El examen técnico consiste en el diseño y desarrollo de una API REST que cumplira las siguientes funcionalidad:
 - Analizar una secuenca de ADN y determinar si la misma correspnde a un Humano o Mutante.
 - Consultar el ratio de mutantes / humanos luego de cada analisis que fue realizado con cada api request.de las requests al api.
 
# Indice

-  [Examen](#examen)
  -  [Especificaciones](#especificaciones)
  -  [Implementación y tecnologias usadas](#implementaci%C3%B3n-y-tecnologias-usadas)
  -  [Notas](#notas)
-  [Setup](#setup)
  -  [Instrucciones](#instrucciones)
  -  [Uso](#uso)
  -  [API Url](#api)
  -  [Servicios](#servicios)
     -  [Es mutante](#es-mutante)
     -  [Estadisticas](#estadisticas)
-  [JavaDoc](#javadoc)
-  [Test](#test)
  -  [Automaticos](#automaticos)
  
## Examen

### Especificaciones

El enunciado del Examen con los requerimientos a resolver se encuentran en la carpeta `doc`. Dentro de ella
se encuentra [un pdf](./doc/Enunciado/Examen%20Mercadolibre%202017.pdf) que describen los distintos requerimientos esperados
a resolver en el proyecto.

### Implementacion y tecnologias usadas

- [Spring Boot](https://projects.spring.io/spring-boot/)
- [SL4FJ](https://www.slf4j.org/)
- [MongoDb](https://www.mongodb.com/)
- [mLab](https://mlab.com/)
- [AWS](https://aws.amazon.com/)


### Notas
Al comenzar con el examen no estaba familiarizado con el uso de AWS, por lo que luego de leer la documentacion y de poder
configurarlo correctamente, comprendiendo los conceptos basicos del servicio elasticbeanstalk. 

Dicho servicio es el que utilizo para hostear la solucion desarrollada, cumpliendo los requerimientos pedidos en el enunciado
del examen.

## Setup

### Instrucciones
Para poder correr la app de forma local unicamente son necesarios 2 requisitos:
  - Java JDK 1.8
  - Maven

Una vez que confirmamos que cumplimos ambos requisitos se deben realizar los siguientes pasos:
  - Clonar este repositorio: https://gitlab.com/vfuentes/MercadoLibre-Mutant
  - Ejecutar desde su IDE la clase MutantDetector para iniciar la App.

Una vez que la aplicacion se inicio, se pueden realizar las request a la API.

El puerto por defecto de la API es 8080.

Tambien se puede iniciar la aplicacion con el siguiente comando en linea de comandos posicionandose en el directorio raiz
del proyecto:
```
mvn exec:java -Dexec.mainClass="ar.com.mercadolibre.mutants.MutantDetectorApp"
```

### Uso

Para iniciar la aplicación de forma local, por favor lea el apartado [Instrucciones](#instrucciones) 

A su vez la App se encuentra hosteada en AWS.

### API Url

URL local: http://localhost:8080

URL hosteada en Amazon Web Service: http://detect-mutant.sa-east-1.elasticbeanstalk.com

### Servicios
#### Es mutante

Request: 
- POST http://detect-mutant.sa-east-1.elasticbeanstalk.com/mutant/

Request body (secuencia ADN mutante):

```
  {"dna":["TTTTGA", "ATGTGC", "AGTTGG", "AGATGG", "CCCCTA", "TCGCTG"]}
```

Response:

```
  200 OK
```
Request body (secuencia ADN humano):

```
  {"dna":["AAAAAA", "TCCTTC", "GTCTGG", "TGTTTG", "ACAGTA", "ACTCAG"]}
```

Response:

```
  403 Forbidden
```
Request body (secuencia ADN erronea):

```
  {"dna":["ABGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA", "TCGCTG"]}
```

Response:

```
  400 Bad RequestForbidden
```

#### Estadisticas

Request: 
- GET http://detect-mutant.sa-east-1.elasticbeanstalk.com/stats

Response: 200 (application/json)

```
{
    count_mutant_dna: 3,
    count_human_dna: 2,
    ratio: 1.5
}
```

### JavaDoc

Los distintos JavaDocs del proyecto se encuentran en las distintas Clases y Metodos del proyecto.

Para un facil acceso y consulta de los mismos, se genero el JavaDoc Site, el cual se encuentra en `doc/JavaDoc`. Dentro de ella
se encuentra [el index](./doc/JavaDoc/index.html).


### Test

#### Automaticos

Para la ejecucion de los test automaticos utilice spring-boot-starter-test y spring-restdocs-mockmvc y 
realice la configuracion del GITLAB CI para que los test se ejecuten con cada deploy.

Para poder probar los distintos componentes y no depende de la disponibilidad de la DB, utilizando Mockito me encargue 
de mockear la interaccion de los componentes con la DB y viceversa.

A su vez utilizando spring-restdocs-mockmvc me encargue de poder mockear la API REST para poder comprobar el 
correcto funcionamiento de los controllers.
