# Examen de Mercado Libre - isMutant
Autor: Pablo Lucero

[![Coverage Status](https://coveralls.io/repos/github/palucero/mutant/badge.svg)](https://coveralls.io/github/palucero/mutant)

Hola! Soy programador backend en .NET / PL-SQL / T-SQL pero tomé el examen como un desafío para involucrarme con Java (cada tanto tenemos que salirnos de nuestra zona de confort). Doy fe de que ha sido exigente!!  y he aprendido más de lo que esperaba :)

# Tecnologias Utilizadas

- Aplicacion
    - Java JSF 2.2 : JavaServer Faces: framework estandar para aplicaciones web en Java
    - JUnit 4.0 : framework para escritura y ejecución de Unit Test para Java
    - JDK 1.8
    - JAX-RS + Jersey + Jackson: JAX-RS es el estandar para creacion de Servicio Rest. Jersey y Jacson son implementaciones del mismo para facilitar el trabajo con APIs
    - Apache Maven 3.6.0: Manejador de Dependencias
- Test de API y Perfomance
    - Apache JMETER
    - Postman
- IDE
    - Eclipse
- Servidores de Aplicaciones
    - Google App Engine (Cloud Java Server)  
    - GlassFish 5.0 (Local Java Servers)
    - Tomcat 9.0 (Local Java Servers)
- Servidores de BD
    - Google SQL (Cloud MySQL 5.7)

# Tecnologias Descartadas

- Servicio de almacenamientos de datos.
    - Google Firebase: se descartó porque la funcionabiliad para hacer llamadas asincrónas requerían la activación de un background proccess que no esaba disponible en la cuenta. Si esta funcionalidad la persistencia tenía una gran latencia afectando al rendimiento de la API
    - Google DataStore : se implementó almacenamiento síncrono con gran tiempo de latencia. No hallé forma efectiva de implenetar la forma asíncrona (tema que invesigaré a futuro)


# Servicios
Se encuentran desplegados en Server Java de App Engine de la plataforma Google Cloud
Uri Base: https://complete-sector-222611.appspot.com/api/service/
## stats

  - https://complete-sector-222611.appspot.com/api/service/stats
##### Objetivo
Retorna estadística sobre las detecciones de genes humanos y muntantes
##### Request
-   HTTP Method: POST
-   Content-Type: no requerido o text/plain
-   No requiere input de datos
##### Response
JSON de salida

    {
        "count_mutant_dna": 5,
        "count_humnan_dna": 2,
        "ratio": 0.71428573
    }

## mutant

-   https://complete-sector-222611.appspot.com/api/service/mutant
##### Objetivo
Procesa una cadena de ADN de NxN.
Devolviendo:  
-   HTTP Status 200 si detecta dos genes mutantes
-   HTTP Status 403 si detecta menos de dos genes mutantes
##### Request
-   HTTP Method: POST
-   Content-Type: Application/JSON

##### JSON de Ingreso
     {
        "dna": [
        "TCGT",
        "AGTC",
        "TTAT",
        "TGAG"]
    }
##### Response
- HTTP Status 200 o 403

## TestMutan
- https://complete-sector-222611.appspot.com/api/service/TestMutant?dimension=100
##### Objetivo
Servicio de prueba de isMutantun. El mismo genera una matriz cuadrada de tamaño = dimension y es procesada 
Devolviendo:  
-   HTTP Status 200 si detecta dos genes mutantes
-   HTTP Status 403 si detecta menos de dos genes mutantes
##### Request
-   HTTP Method: POST
-   Content-Type: Application/JSON
-   QueryParama en URI bajo forma ?dimension=x (donde x (int) generará una matríz de YxY )
##### Response
- HTTP Status 200 o 403
## ArrayTest
- https://complete-sector-222611.appspot.com/api/service/ArrayTest?dimension=20
##### Objetivo
Servicio que genera una matriz cuadrada de tamaño = dimension y es retornada en estructura JSON dna. Util para probar manualmente el servicio /mutant
 ##### Request
-   HTTP Method: GET
-   Content-Type: text/plain
-   QueryParama en URI bajo forma ?dimension=x (donde x (int) generará una matríz de YxY )
##### Response
JSON de salida

    {
        "dna": [
        "GCTCAC",
        "ATCAAT",
        "GAGAGT",
        "CATTAC",
        "TGGACA",
        "CCCCCC"
        ]
    }


### Uso del proyecto
Requerimientos
##### Hard
- Equipo con más 4GB de RAM y 2GB de disco libre
##### Soft
- IDE Eclipse: https://www.eclipse.org/
- JDK 1.8: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Apache Maven 3.6: https://maven.apache.org/
- GlassFish: https://javaee.github.io/glassfish/download
- TomCat 9: https://javaee.github.io/glassfish/download
- Postmant: https://www.getpostman.com/
**Configurar variables de Sistema JAVA_HOME al raiz de la instalación del JDK y agregar a PATH la ruta hacia la carpta bin de Maven

### Pasos (Maven)
Descargar fuentes mediante cliente GIT a su equipo Local
Posicionarse dentro del raiz /mutant donde se encuentra el archivo POM.XML
Ejecutar:
```sh
$ mvn --v
```
Con esto se valida versión de Maven (debe ser 3.6 o mayor)
Ejecutar:
```sh
$ mvn celan install
```
Esto lanzará el buil del proyecto más el empaquetado en war.
Buscar el war en mutant\target debería tener el nombre mutant-1.0-SNAPSHOT.war
Desplegar en GlassFish o Tomcat según su preferencia.

URI de prueba:
Dependiendo del server pueden cambiar los contextos:
http://localhost:Puerto/mutant/api/service/ArrayTest?dimension=20
http://localhost:Puerto/mutant-1.0-SNAPSHOT/api/service/ArrayTest?dimension=20

### Pasos (IDE)
Descargar fuentes mediante cliente GIT a su equipo Local
Abrir Eclipse.
Abrir proyecto desde "Open Projects from File Systems"
Seleccionar la carpeta /mutant de los fuentes.
Agregar server Tomcat 
Ir Run > Run As > Run On Server
Luego del despliegue quedará operativa la aplicación.

URI de prueba:
Dependiendo del server pueden cambiar los contextos:
http://localhost:Puerto/mutant/api/service/ArrayTest?dimension=20
http://localhost:Puerto/mutant-1.0-SNAPSHOT/api/service/ArrayTest?dimension=20

