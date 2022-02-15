import 'package:flutter/material.dart';
import 'package:productos_app/screens/main_menu.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';


class CheckAuthScreen extends StatelessWidget {

  static const String id = 'check_screen';

  @override
  Widget build(BuildContext context) {

    final authService = Provider.of<AuthService>(context, listen: false);

    return Scaffold(
      body: Center(
        child: FutureBuilder(
          future: authService.readToken(),
          builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
            if (!snapshot.hasData) {
              return SplashScreen(); 
            }
              // return CircularProgressIndicator(color: Colors.indigo,);

            if (snapshot.data == '') {
              // Se ejecuta una vez termine la construcción de widget
              Future.microtask(() {
                Navigator.pushReplacement(context, PageRouteBuilder(
                  pageBuilder: (_, __, ___) => LoginScreen(),
                  transitionDuration: Duration(seconds: 0)
                ));
              });
            } else {
              Future.microtask(() {
                Navigator.pushReplacement(context, PageRouteBuilder(
                  pageBuilder: (_, __, ___) => MainMenuScreen(),
                  transitionDuration: Duration(seconds: 0)
                ));
              });
            }

            return Container();
          }
        )
     ),
   );
  }

}