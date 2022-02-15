void main() {
  listas();
}

/**
 * las listas son en base a 0
 * dynamic es un tipo generico, por lo que acepta lo que sea
 */
void listas() {
  // una forma de crear listas
  // esta es una lista Dynamic, por lo que va a aceptar cualquir tipo de dato
  List numeros = [];
  List numerosPar = [2, 4, 6, 8, 10];

  print(numeros);
  print(numerosPar);

  numerosPar.add(12);
  print(numerosPar);

  print(numerosPar[0]);
  numerosPar.add("Un valor");
  numerosPar.forEach((element) => print(element));

  print("\n");
  // Podemos asignar una lista de un tipo de dato
  List<int> nums = [];
  nums.add(1);
  nums.add(2);
  nums.forEach((element) => print(element));

  print("\n");
  // generación de numeros
  final first100Numbers = List.generate(100, (index) => index);
  first100Numbers.forEach((element) => print(element));
}
