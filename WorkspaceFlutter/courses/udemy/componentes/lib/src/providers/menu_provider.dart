import 'dart:convert';

import 'package:flutter/services.dart'
    show rootBundle; // exponemos solo un servicio de este paquete

class _MenuProviders {
  List<dynamic> opciones = [];

  _MenuProviders() {
    // cargarData();
  }

  Future<List<dynamic>> cargarData() async {
    // Leer json data
    final respuesta = await rootBundle.loadString('data/menu_opts.json');

    // Convertir Stirng en Map
    Map dataMap = json.decode(respuesta);
    opciones = dataMap['rutas'];

    return opciones;
  }
}

// Solo creamos una instancia de esta clase
final menuProvider = new _MenuProviders();
