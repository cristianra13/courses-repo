abstract class Animal {}

abstract class Mamifero extends Animal {}

abstract class Ave extends Animal {}

abstract class Pez extends Animal {}

abstract class Volador {
  void volar() => print('Estoy volando');
}

abstract class Caminante {
  void caminar() => print('Estoy caminando');
}

abstract class Nadador {
  void nadar() => print('Estoy nadando');
}

/**
 * Con la palabra reservada with, 
 * identificamo un mixing
 */
class Delfin extends Mamifero with Nadador {}

class Murcielago extends Mamifero with Volador, Caminante {}

class Gato extends Mamifero with Caminante {}

class Pato extends Ave with Caminante, Volador, Nadador {}

main(List<String> args) {
  final delfin = new Delfin();
  delfin.nadar();

  final murcielago = new Murcielago();
  murcielago.volar();
  murcielago.caminar();

  final gato = new Gato();
  gato.caminar();

  final pato = new Pato();
  pato.nadar();
  pato.volar();
  pato.caminar();
}
