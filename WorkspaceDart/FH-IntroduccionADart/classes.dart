class Heroe {
  String nombre;
  String poder;

  // Constructor
  // forma larga
  /* Heroe(nombre, poder) {
    this.nombre = nombre;
    this.poder = poder;
  } */

  // Forma de pasar los args por nombre
  Heroe({required this.nombre, required this.poder});

  // Forma corta constructor
  //Heroe(this.nombre, this.poder);

  // Constructores por nombre
  // Heroe.fromJson(Map<String, String> parseJson) {
  //   this.nombre = parseJson['nombre']!;
  //   this.poder = parseJson['poder']!;
  // }

  /**
   * Los dos puntos indican que se va a ejecutar al momento de
   * que se este creando la instancia de la clase
   * Es decir, que se va a ejecutar this.nombre = parseJson['nombre']!,
        this.poder = parseJson['poder']!; al momento de crear la instancia

        el signo de admiración, es cuando estamos seguros de que vamos a recibir el valor,
        en caso contrario, agregar ?? 'XXXXX'.
   */
  Heroe.fromJson(Map<String, String> parseJson)
      : this.nombre = parseJson['nombre']!,
        this.poder = parseJson['poder'] ?? 'not-data';

  @override
  String toString() {
    return 'HEROE: nombre: ${this.nombre}, poder: ${this.poder}';
  }
}

void main() {
  // la palabra new puede ser obcional en Dart

  // es recomendable agregar new al crear una instancia de una clase
  // final deadPool = new Heroe('Wayne', 'Regeneración');
  // Heroe capAmerica = new Heroe('Steve Rogers', 'Super Fuerza');

  final rawJson = {'nombre': 'Tony', 'poder': 'Millonario'};

/**
 * Si no se agrega el signo de admiración, dart lo interpreta como
 * un error ya que puede ser que no venga un valor o sea nulo alguno 
 * del mapa
 */

  // deadPool.nombre = 'Wayne';
  // deadPool.poder = 'Regeneración';

  // capAmerica.nombre = 'Steve Rogers';
  // capAmerica.poder = 'Super Fuerza';

// Acá imprime -> instance of 'Heroe'
  // print(deadPool);
  // print(capAmerica);

  // final ironMan =
  //     new Heroe(nombre: rawJson['nombre']!, poder: rawJson['poder']!);

  // print(ironMan);

  final ironman = Heroe.fromJson(rawJson);
  print(ironman);
}
