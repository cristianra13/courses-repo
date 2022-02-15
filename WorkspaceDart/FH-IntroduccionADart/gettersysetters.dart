import 'dart:math' as math;

void main() {
  final cuadrado = new Cuadrado(5);
  cuadrado.area = 25;

  print('area: ${cuadrado.calcularRea()} ');
  print('lado: ${cuadrado.lado}');
  print('area get: ${cuadrado.area}');
}

class Cuadrado {
  double lado;

// lado siempre va a estár inicializado
  Cuadrado(double lado) : this.lado = lado;

  // getter
  double get area {
    return this.lado * this.lado;
  }

  // setter
  set area(double valor) {
    this.lado = math.sqrt(valor);
  }

  calcularRea() {
    return this.lado * this.lado;
  }
}
