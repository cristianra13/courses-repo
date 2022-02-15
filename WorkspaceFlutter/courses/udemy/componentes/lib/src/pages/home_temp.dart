import 'package:flutter/material.dart';

class HomePageTemp extends StatelessWidget {
  final opciones = ['Uno', 'Dos', 'Tres', 'Cuatro', 'Cinco'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Componentes Temp'),
        ),
        body: ListView(
          //children: _crearItem()
          children: _crearItemCorto()
        ));
  }

  // una forma de crear la lista de Widgets
  List<Widget> _crearItem() {
    // Transformación de lista a widgets
    List<Widget> lista = [];

    for (String opcion in opciones) {
      final tempWidget = ListTile(
        title: Text(opcion),
      );

      // Dart ofrece operador de cascada con ..
      lista..add(tempWidget)..add(Divider());
    }

    return lista;
  }

  // Otra forma de crear la lista de Widgets
  List<Widget> _crearItemCorto() {
    return opciones.map((item) {
      return Column(
        children: [
          ListTile(
            title: Text(item + '!'),
            subtitle: Text('Cualquier cosa'),
            leading: Icon( Icons.account_balance_wallet ),
            trailing: Icon(Icons.keyboard_arrow_right),
            onTap: () {},
            ),
          Divider()
        ],
      );
    }).toList();
  }
}
