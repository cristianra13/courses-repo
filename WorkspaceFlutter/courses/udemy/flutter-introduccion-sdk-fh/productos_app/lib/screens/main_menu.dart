
import 'package:flutter/material.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:productos_app/widgets/widgets.dart';
import 'package:provider/provider.dart';


class MainMenuScreen extends StatelessWidget {

  static const String id = 'main_menu_screen';

  @override
  Widget build(BuildContext context) {

    final authService = Provider.of<AuthService>(context, listen: false);

    return Scaffold(
      // appBar: AppBar(
      //   automaticallyImplyLeading: false,
      //   actions: [
      //     IconButton(
      //       icon: Icon(Icons.login_outlined),
      //       onPressed: () {
      //         authService.logout();
      //         Navigator.pushReplacementNamed(context, LoginScreen.id);
      //       },
      //     ),
      //   ],
      // ),
      body: Stack(
        children: [
          // Background
          Background(),

          // Main menu body
          _MainMenuBody()
        ],
      ),
      // bottomNavigationBar: CustomBottomNavigation(),
    );
  }
}

class _MainMenuBody extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [

          SizedBox(height: 10,),
          // Títulos
          PageTitle(),

          // card Table
          CardTable()
        ],
      ),
    );
  }

}