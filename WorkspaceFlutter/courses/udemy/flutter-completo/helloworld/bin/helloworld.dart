import 'dart:convert';
import 'dart:io';

import 'package:helloworld/helloworld.dart' as helloworld;

void main(List<String> arguments) {
  variablesLista();
  print('\n');
  variablesMapas();
  print('\n');
  switchCase();
  print('\n');
  cicloFor();
  print('\n');
  cicloWhile();
  print('\n');
  cicloDoWhile();
  print('\n');
  sentenciaAssert();
  print('\n');
  expresionSigno();
}

void variablesLista() {
  List<String> autos = [];

  // Añadir elementos a la lista
  autos.add("Audi");
  autos.add('Mercedez');
  autos.add('Ferrari');
  print(autos);

  // Borrar un elemento de la lista
  autos.remove('Ferrari');
  print(autos);

  // Editar un elemento de la lista
  autos[0] = 'VolsWagen';
  print(autos);

  // Obtener tamaño de la lista
  int size = autos.length;
  print('Tamaño de la lista: $size');

  // Validar si un elemento está en la lista
  bool existe = autos.contains('Mercedez');
  print("El valor existe: $existe");

  existe = autos.contains('Ferrari');
  print("El valor existe: $existe");
}

void variablesMapas() {
  // Inicialización acotada
  Map map = {1: 'Uno', 2: 'Dos', 3: 'Tres'};

  Map map2 = Map();
  map2[1] = 'Uno';
  map2[2] = 'Dos';
  map2[3] = 'Tres';

  print('keys: ${map2.keys} - values: ${map2.values}');
}

void switchCase() {
  print('Switch');
  int anio = 2021;

  // leer por terminal el valor del año
  // var anio = stdin.readLineSync(encoding: Encoding.getByName('utf-8'));

  switch (anio) {
    case 2020:
      print('Año $anio');
      break;
    case 2021:
      print('Año $anio');
      break;
    case 2022:
      print('Año $anio');
      break;
    default:
      print('Sin resultado');
  }
}

void cicloFor() {
  print('Ciclo For');
  List<int> numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0];

  for (int i = 0; i < numeros.length; i++) {
    print('Numero: $i');
  }
  print('---------');
  for (int i in numeros) {
    print('Numero: $i');
  }
}

void cicloWhile() {
  print('Ciclo While');
  int counter = 0;

  while (counter <= 5) {
    print('Counter: $counter');
    counter++;
  }
}

void cicloDoWhile() {
  print('Ciclo Do-While');
  int counter = 9;
  do {
    print('Counter: $counter');
    counter++;
  } while (counter <= 10);
}

// sentencia Assert
/**
 * Se puede usar para validar que un objeto no venga null
 */
void sentenciaAssert() {
  var texto = "--";
  assert(texto != null);
  assert(texto == null);
}

// expresión ? u operador ternario
void expresionSigno() {
  (1 == 1) ? print('Son iguales') : print('No son iguales');
}
