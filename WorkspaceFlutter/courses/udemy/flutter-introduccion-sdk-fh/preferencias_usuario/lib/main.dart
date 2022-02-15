import 'package:flutter/material.dart';
import 'package:preferencias_usuario/src/pages/home_page.dart';
import 'package:preferencias_usuario/src/pages/settings_page.dart';
import 'package:preferencias_usuario/src/share_preferences/preferencias_usuario.dart';


void main()  async{
  // No se carga la aplicación hasta que las preferencias no se han cargado
  WidgetsFlutterBinding.ensureInitialized();
  final prefs = PreferenciasUsuario();
  await prefs.initPrefs();
 runApp(MyApp());
}
  

class MyApp extends StatelessWidget {

  final prefs = PreferenciasUsuario();

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Preferencias',
      initialRoute: prefs.ultimaPagina,
      routes:  {
        HomePage.routeName : (BuildContext context) => HomePage(),
        SettingsPage.routeName : (BuildContext context) => SettingsPage()
      },
    );
  }

}