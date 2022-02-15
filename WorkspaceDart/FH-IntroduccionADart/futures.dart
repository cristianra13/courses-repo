/**
 * Los futures son muy importantes en Dart
 * 
 * Un Future es una tarea asincrona
 */

void main() async {
  print('Antes de la petición');

  final data = await httpGet('https://nasa.com/aliens');
  print(data);

  // .then() es lo que se ejecuta despues de que se resuelva el Future
  // httpGet('https://nasa.com/aliens').then((data) => print(data));

// resolvemos el Future con then
  // getNombre('123abc').then((value) => print(value));

  // await solo funciona dentro de fucniones asincronas
  final nombre = await getNombre('12');
  getNombre('123abc').then(print);

  print('Fin del programa');
}

/**
 * Si se agrega un async a la función, significa que
 * esta ahora va a retornar un Future
 */
Future<String> getNombre(String id) async {
  return '$id - Cristian';
}

Future httpGet(String url) {
  return Future.delayed(Duration(seconds: 3), () => 'hola mundo - 3 segundos');
}
