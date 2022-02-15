import 'package:componentes/src/pages/listview_page.dart';
import 'package:componentes/src/pages/slider_page.dart';
import 'package:componentes/src/routes/routes.dart';

import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      // Quitamos el banner
      debugShowCheckedModeBanner: false,
      title: 'Componentes App',
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: [
        Locale('en', 'EN'),
        Locale('es', 'ES'),
      ],
      // home: HomePage()
      initialRoute: '/', // Ruta inicial
      // Definimos nuestras rutas
      routes: getApplicationRoutes(),
      onGenerateRoute: (settings) {
        print('Ruta llamada: ${settings.name}');

        return MaterialPageRoute(
          builder: (BuildContext context) => ListaPage()
        );
      },
    );
  }
}
