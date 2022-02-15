void main() {
  final superman = new Heroe();
  superman.nombre = 'Klark kent';
  superman.poder = 'Todos';
  superman.valentia = 100;

  final luthor = new villano();
  luthor.nombre = 'Lex Luthor';
  luthor.poder = 'Inteligencia';
  luthor.maldad = 100;
}

abstract class Personaje {
  String? nombre;
  String? poder;
}

class Heroe extends Personaje {
  int valentia = 0;
}

class villano extends Personaje {
  int maldad = 0;
}
