void main() {
  final superman = new Heroe('Clark Kent');
  final luthor = new Villano('Lex Luthor');
  luthor.poder = 'Intenligencia';

  print(superman);
  print(luthor);
}

abstract class Personaje {
  String? poder;
  String nombre;

  Personaje(this.nombre);

  @override
  String toString() {
    return '$nombre - $poder';
  }
}

class Heroe extends Personaje {
  int nivelValentia = 100;

  Heroe(String nombre) : super(nombre);
}

class Villano extends Personaje {
  int nivelMaldad = 100;

  Villano(String nombre) : super(nombre);
}
