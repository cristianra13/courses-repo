import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Style para font
    final TextStyle textStyle = new TextStyle(fontSize: 25);

    int conteo = 10;

    return Scaffold(
      
        appBar: AppBar(title: Text('Título'), centerTitle: true),

        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text('Número de taps', style: textStyle),
              Text('$conteo', style: textStyle)
            ],
          ),
        ),
        
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add_outlined, size: 28),
          onPressed: () {
            conteo++;
          },
        ),
        // Posición del botón
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat);
  }
}
