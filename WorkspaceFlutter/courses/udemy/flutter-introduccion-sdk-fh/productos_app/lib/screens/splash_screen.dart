import 'dart:async';

import 'package:flutter/material.dart';
import 'package:productos_app/providers/restaurant_provider.dart';
import 'package:productos_app/screens/main_menu.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';

class SplashScreen extends StatefulWidget {
  static const String id = 'splash_screen';

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  // @override
  // void initState() {
  //   super.initState();
  //   Timer(Duration(seconds: 3), () {
      
  //   });
  // }

  @override
  Widget build(BuildContext context) {

    final logoService = Provider.of<RestaurantProvider>(context);
    final authService = Provider.of<AuthService>(context, listen: false);
    Timer(Duration(seconds: 4), () async {
      final response = await authService.readToken();
      print(response);

      if (await authService.readToken() == 'no-token') {
        Navigator.pushNamed(context, LoginScreen.id);
      } else {
        Navigator.pushNamed(context, MainMenuScreen.id);
      }
      

    });

    return Scaffold(
      body: Center(
        child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Logo here
            Container(
              child: logoService.logoModel == null
              ? Image(image: AssetImage('assets/init_logo.png'))
              : Image(image: NetworkImage(logoService.logoModel!.urlLogo))
            ),

            SizedBox(height: 20,),
            CircularProgressIndicator(
              valueColor: AlwaysStoppedAnimation<Color>(Colors.black),
            )
          ],
        ),
      ),
    );
  }
}