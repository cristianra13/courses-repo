void main() async {
  print('Vamos a pedir los datos');

  await httpGet("https://api.aliens/api/v1/aliens")
      .then((value) => print(value));

  print('ültima línea');
}

// Esto es un future
Future<String> httpGet(String url) {
  return Future.delayed(new Duration(seconds: 4), () {
    return 'Hola mundo';
  });
}
