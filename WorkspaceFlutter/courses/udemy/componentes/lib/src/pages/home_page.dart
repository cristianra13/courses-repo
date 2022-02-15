import 'package:componentes/src/pages/alert_page.dart';
import 'package:flutter/material.dart';

import 'package:componentes/src/providers/menu_provider.dart';
import 'package:componentes/src/utils/icono_string_util.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Componentes')),
      body: _lista(),
    );
  }

  Widget _lista() {
    // print(menuProvider.cargarData());
    // menuProvider.cargarData().then((opciones) {
    //   print('Lista');
    //   print(opciones);
    // });

    return FutureBuilder(
      // Asignamos a future, el retorno del método cargarData()
      future: menuProvider.cargarData(),
      initialData: [],
      // dejamos solo context ya que viene embebido la clase BuildContext
      builder: (context, AsyncSnapshot<List<dynamic>> snapshot) {
        return ListView(
          children: _crearListaItem(snapshot.data!, context),
        );
      },
    );

    // return ListView(
    //   children: _crearListaItem(),
    // );
  }

  /**
   * Método para rear lista de items
   */

  List<Widget> _crearListaItem(List<dynamic> data, BuildContext context) {
    final List<Widget> opciones = [];

    data.forEach((opcion) {
      final widgetTemp = ListTile(
        title: Text(opcion['texto']),
        leading: geticon(opcion['icon']), // enviamos el nombre del icono
        trailing: Icon(Icons.keyboard_arrow_right),
        onTap: () {
          Navigator.pushNamed(context, opcion['ruta']);

          // Está es una forma de hacerlo
          // final route = MaterialPageRoute(
          //   builder: (context) => AlertPage()
          // );

          // //Navegamos a una nueva pantalla
          // Navigator.push(context, route);
        },
      );

      opciones..add(widgetTemp)..add(Divider());
    });

    return opciones;
  }
}
