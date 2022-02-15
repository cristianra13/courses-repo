void main() {
  final nombre = 'Cristian';
  saludar(nombre);
  saludo();
  saludoConArgsOpcYRequeridos(nombre);
  saludoConArgsOpcYRequeridos("Hola ", nombre);
  printMensaje(null);
  printArgs(apellido: 'Real', nombre: 'Cristian');
  printArgsRequire(nombre: 'Cristian', apellido: 'Real');
}

void saludar(String nombre) {
  print('Hola Mundo $nombre');
}

// función con argumentos opcionales
void saludo([nombre = 'no-name']) {
  print('Hola $nombre');
}

void saludoConArgsOpcYRequeridos(String nombre, [mensaje = 'no-message']) {
  print('$mensaje $nombre');
}

/**
 * Funciones con argumentos que pueden ser nulos
 */
void printMensaje(String? mensaje) {
  print('El mensaje es $mensaje');
}

/**
 * Arguentos sin orden especifico
 * 
 * Podemos llamarlo así:
 * printArgs(apellido: 'Real', nombre: 'Cristian');
 */
// void printArgs({String nombre = '', String apellido = ''}) {
void printArgs({String? nombre, String? apellido}) {
  print('$nombre $apellido');
}

/**
 * Requerir argumentos obligatoriamente
 */
void printArgsRequire({required String nombre, required String apellido}) {
  print('$nombre $apellido - dev');
}
