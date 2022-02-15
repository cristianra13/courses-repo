import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rq_reader/pages/home_page.dart';
import 'package:rq_reader/pages/mapa_page.dart';
import 'package:rq_reader/providers/scan_list_provider.dart';
import 'package:rq_reader/providers/ui_provider.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    // Creamos las rutas para la app
    return MultiProvider(
      // agregamos el provider al context
      providers: [
        // Le disponemos al context las instancias de los providers
        ChangeNotifierProvider(
          create: (_) => UiProvider()
        ),
        ChangeNotifierProvider(
          create: (_) => ScanListProvider()
        )
      ],

      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'QR Reader',
        initialRoute: 'home',
        routes: {
          'home': ( _ ) => HomeScreen(),
          'mapa': ( _ ) => MapaScreen()
        },
        theme: ThemeData(
          primarySwatch: Colors.deepPurple,
        )
      ),
    );
  }
}