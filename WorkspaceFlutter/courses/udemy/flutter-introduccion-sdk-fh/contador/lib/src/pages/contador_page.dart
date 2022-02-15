import 'package:flutter/material.dart';

class ContadorPage extends StatefulWidget {
  @override
  createState() => _ContadorPageState();
}

// Clase privada que va a manejar el estado de ContadorPage
class _ContadorPageState extends State<ContadorPage> {
  // Style para font
  final _textStyle = new TextStyle(fontSize: 25);
  int _conteo = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text('Stateful'), centerTitle: true),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text('Número de taps', style: _textStyle),
              Text('$_conteo', style: _textStyle)
            ],
          ),
        ),
        floatingActionButton: _crearBotones()

        // Posición del botón
        // floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat
        );
  }

  // Crear botones
  Widget _crearBotones() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: [
        SizedBox(width: 30),

        FloatingActionButton(
          onPressed: () => _reset(),
          child: Icon(Icons.exposure_zero),
        ),
        
        Expanded(child: SizedBox()),

        FloatingActionButton(
          onPressed: () => _sustraer(),
          child: Icon(Icons.remove),
        ),

        SizedBox(
          width: 5.0,
        ),

        FloatingActionButton(
          onPressed: () => _agregar(), // Ya que solo es una línea, mandamos la referencia a la función
          child: Icon(Icons.add),
        )
      ],
    );
  }

  // Función onPressed
  void _agregar() {
    setState(() =>_conteo++);
  }

  void _sustraer() {
    setState(() => _conteo--);
  }

  void _reset() {
    setState(() => _conteo = 0);
  }
}
