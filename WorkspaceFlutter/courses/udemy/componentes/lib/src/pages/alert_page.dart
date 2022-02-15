import 'package:flutter/material.dart';

class AlertPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Alert page'),
        ),
        body: Center(
          child: ElevatedButton(
              child: Text('Mostrar Alerta'),
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all<Color>(Colors.blue),
              ),
              onPressed: () => _mostrarAlerta(context)),
        ),
        floatingActionButton: FloatingActionButton(
            child: Icon(Icons.backup_rounded),
            onPressed: () {
              // Volver a la pantalla anterior
              Navigator.pop(context);
            }));
  }

  void _mostrarAlerta(BuildContext context) {
    showDialog(
        context: context,
        barrierDismissible: false,
        builder: (context) {
          return AlertDialog(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(20.0)
            ),
            title: Text('Título'),
            content: Column(
              // Evitar que la tarjeta se tome todo el larog de la pantalla
              mainAxisSize: MainAxisSize.min,
              children: [
                Text('Este es el contenido de la caja de la alerta'),
                FlutterLogo(size: 100)
              ],
            ),
            actions: [
              TextButton(
                  child: Text('Cancelar'),
                  // Una forma de cerrar el Dialog
                  onPressed: () => Navigator.of(context).pop()),
              TextButton(
                  child: Text('OK'),
                  onPressed: () {
                    // Otra forma de cerrar el Dialog
                    Navigator.of(context).pop();
                  })
            ],
          );
        });
  }
}
