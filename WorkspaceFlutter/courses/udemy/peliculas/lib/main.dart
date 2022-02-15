
import 'package:flutter/material.dart';
import 'package:peliculas/providers/movie_provider.dart';
import 'package:peliculas/screens/screens.dart';
import 'package:provider/provider.dart';
 
 /**
  * Se carga primero AppState() antes de MyApp()
  */
void main() => runApp(AppState());

// Acá vamos a mantener el estado de la apliación
class AppState extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return MultiProvider(
      providers: [
        //Inicializamos la instancia de MovieProvider
        ChangeNotifierProvider(
          create: (_) => MoviesProvider(),
          /**
           * Si lazy se deja en true, hace carga peresoza por lo que no crea la instancia de la clase hasta
           * que sea invocada por otra
           */
          lazy: false, // Tan pronto se crea este widget, manda a llamar la inicialización del mismo
        ),
      ],
      child: MyApp(),
    );
  }

}
 
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Películas',
      initialRoute: 'home',
      // Definimos nuestras rutas -> mapa
      routes: {
        'home': ( _ ) => HomeScreen(),
        'details': ( _ ) => DetailsScreen(),
      },
      // hacemos una copia del tema lisgh, eceptuando las modificaciones que hagamos
      theme: ThemeData.light().copyWith(
        // Cambiamos el color del AppBar
        appBarTheme: AppBarTheme(
          color: Colors.indigo
        )
      ),
    );
  }
}