void main() {
  final perro = new Perro();
  final gato = new Gato();

  sonidoAnimal(perro);
  sonidoAnimal(gato);
}

/**
 * Recibimos algún objeto que haya implementado de la
 * clase Animal
 */
void sonidoAnimal(Animal animal) {
  animal.emitirSonido();
}

abstract class Animal {
  int? patas;

  Animal();

  void emitirSonido() {}
}

class Perro implements Animal {
  int? patas;

  void emitirSonido() => print('guuuaauuuu');
}

class Gato implements Animal {
  int? patas;
  int? bigotes;

  void emitirSonido() => print('Miauuuuu');
}
