import 'dart:ffi';

void main() {
  print('Hola mundo');
  variables();
  booleanos(true);
  nullVariables();
}

void comments() {
  // This is a comment

  /**
   * this is a muiltiline comment
   */
}

void variables() {
  // Strings
  // Con está manera no sabemos especificamente el tipo de dato
  var nombre = 'Cristian';
  var apellido = 'Real';

  print(nombre + apellido);
  print('$nombre $apellido');

  // Agregamos explicitamente el tipo de dato
  String segundoNombre = 'Daniel';
  String segundoApellido = 'Ariza';

  // variables final -> solo se puede asignar una vez
  final String direccion = 'Dirección';
  print(direccion);

  // constante -> su valor nunca va a cambiar en tiempo de compilación
  const edad = 28;
  print(edad);

  // Números
  // entero sin decimales
  int empleados = 10;
  double valores = 1000.25;
  print('$empleados $valores');
}

/**
 * para decir que una variable puede tener un valor nulo,
 * podemos agregar el valor ? despues del tipo de dato de la variable
 * ej:
 * String? nombre = null;
 */
void booleanos(isActive) {
  if (isActive) {
    print('Está activo');
  } else {
    print('Está inactivo');
  }

  if (!isActive) {
    print('No Estás Activo');
  }
}

void nullVariables() {
  String? nombre = null;

  if (nombre == null) {
    print('variable nula');
  }
}
