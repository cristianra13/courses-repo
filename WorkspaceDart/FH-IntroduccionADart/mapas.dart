void main() {
  Map persona = {'nombre': 'Cristian', 'apellido': 'Real', 'edad': 28};
  print(persona);
  print(persona['nombre']);

  Map<String, String> datosPersonales = new Map();

  datosPersonales.addAll({'direccion': 'Cra 100 100'});
  datosPersonales.addAll({'número celular': '300000000'});

  print(datosPersonales);
}
