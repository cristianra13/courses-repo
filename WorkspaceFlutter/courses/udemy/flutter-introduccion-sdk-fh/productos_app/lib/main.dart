import 'package:flutter/material.dart';
import 'package:productos_app/providers/restaurant_provider.dart';
import 'package:productos_app/screens/main_menu.dart';
import 'package:productos_app/screens/products_cart_page.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/screens/splash_screen.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';
import 'package:firebase_core/firebase_core.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(AppState());
}

/**
 * Podemos usar nuestro product service en cualquier momento de la app
 */
class AppState extends StatelessWidget {

  @override
  Widget build(BuildContext context) {   

    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          // Va a ser lazy, hasta el momento que se necesite llamar
          create: (_) => ProductService()
        ),
        ChangeNotifierProvider(create: (_) => RestaurantProvider()),
        /**
         * Con esto, ya podemos usarla en cualquier parte de la aplicación,
         * saber que usuario está conectado, su token, etc
         */
        ChangeNotifierProvider(create: (_) => AuthService()),
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
      title: 'Restaurante Sena',
      initialRoute: SplashScreen.id,
      routes: {
        CheckAuthScreen.id: (_) => CheckAuthScreen(),
        HomeScreen.id: (_) => HomeScreen(),
        LoginScreen.id: (_) => LoginScreen(),
        MainMenuScreen.id: (_) => MainMenuScreen(),
        ProductScreen.id: (_) => ProductScreen(),
        RegisterScreen.id: (_) => RegisterScreen(),
        SplashScreen.id: (_) => SplashScreen(),
        ProductsCartScreen.id: (_) => ProductsCartScreen()
      },
      scaffoldMessengerKey: NotificationsService.messengerKey,
      theme: ThemeData.light().copyWith(
        scaffoldBackgroundColor: Colors.white,
        appBarTheme: const AppBarTheme(
          elevation: 0,
          color: Color(0x00ff3d00)
        ),
        floatingActionButtonTheme: FloatingActionButtonThemeData(
          backgroundColor: Colors.indigo,
          elevation: 0
        )
      ),
    );
  }

}