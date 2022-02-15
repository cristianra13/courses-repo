void main() {
  Cuadrado cuadrado = new Cuadrado();
  cuadrado.lado = 2;
  print(cuadrado);
  print('Area: ${cuadrado.area}');
}

class Cuadrado {
// Si se pone un guion bajo a las propiedades de las clases al inicio, quedan privadas
  double _lado = 0;
  double _area = 0;

  // setters
  set lado(double valor) {
    if (valor <= 0) {
      // Lanzamos un error
      throw ('El lado no puede ser menor o igual a cero');
    }

    _lado = valor;
  }

  double get area => _lado * _lado;

// toString
  String toString() => 'lado: $_lado - area: $_area';
}
