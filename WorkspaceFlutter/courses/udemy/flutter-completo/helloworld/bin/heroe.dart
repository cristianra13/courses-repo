import 'dart:convert';

void main() {
  // Forma 1
  // final wolverine = new Heroe('Logan', 'Regeneración');

  final String rowJson = '{ "nombre": "Logan", "poder": "Regeneración" }';
  Map parsedJson = json.decode(rowJson);

// utilizado bastante
  final wolverine = new Heroe.fromJson(parsedJson);
  print(wolverine.nombre);
  print(wolverine.poder);

  // print(parsedJson);
}

class Heroe {
  String? nombre;
  String? poder;

  // Forma 1
  Heroe(this.nombre, this.poder);

  // Constructor con nombre // recibimos un map
  Heroe.fromJson(Map parsedJson) {
    nombre = parsedJson['nombre'];
    poder = parsedJson['poder'];
  }

  String toString() => 'nombre: $nombre - poder: $poder';
}
