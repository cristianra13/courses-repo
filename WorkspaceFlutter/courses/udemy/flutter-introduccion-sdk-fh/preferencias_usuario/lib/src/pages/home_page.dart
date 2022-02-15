import 'package:flutter/material.dart';
import 'package:preferencias_usuario/src/share_preferences/preferencias_usuario.dart';
import 'package:preferencias_usuario/src/widgets/menu_widget.dart';

class HomePage extends StatelessWidget {

  // definimos el nombre de ola página como propiedad static
  static final String routeName = 'home';
  final prefs = PreferenciasUsuario();

  @override
  Widget build(BuildContext context) {
    prefs.ultimaPagina = HomePage.routeName;

    return Scaffold(
      appBar: AppBar(
        title: Text('Preferencias de usuario'),
        backgroundColor: (prefs.colorSecundario) ? Colors.teal : Colors.blue,
      ),
      drawer: MenuWidget(),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text('Color secundario: ${prefs.colorSecundario}'),
          Divider(),
          Text('Género: ${prefs.genero}'),
          Divider(),
          Text('Nombre usuario: ${prefs.nombre}'),
          Divider(),
        ],
      ),
    );
  }
  
}