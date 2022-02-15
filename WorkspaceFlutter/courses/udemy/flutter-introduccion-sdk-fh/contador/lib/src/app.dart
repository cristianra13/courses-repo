import 'package:contador/src/pages/contador_page.dart';
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  // Método encargado de dibijar el widget
  @override
  Widget build(BuildContext context) {
    
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Center(
          //child: HomePage()
          child: ContadorPage(),
        )
    );
  }
}
