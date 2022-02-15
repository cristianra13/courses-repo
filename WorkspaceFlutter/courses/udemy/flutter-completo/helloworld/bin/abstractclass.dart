void main() {
  // Esto es incorrecto
  // final perro = new Animal();
  final perro = new Perro();
  perro.emitirSonido();
  final gato = new Gato();
  gato.emitirSonido();
}

abstract class Animal {
  int? patas;
  void emitirSonido();
}

class Perro implements Animal {
  int? patas;

  void emitirSonido() => print("Guaauuu");
}

class Gato implements Animal {
  int? patas;

  void emitirSonido() => print("Miaauuu");
}
