import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';

class PageTitle extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    final authService = Provider.of<AuthService>(context, listen: false);

    return SafeArea(
      bottom: false,
      child: Container(
        margin: EdgeInsets.symmetric(horizontal: 20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  'Menú Principal', 
                  style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold, color: Colors.white )
                ),

                IconButton(
                  icon: Icon(Icons.login_outlined, color: Colors.white, size: 35,),
                  onPressed: () {
                    authService.logout();
                    Navigator.pushReplacementNamed(context, LoginScreen.id);
                  },
                ),
              ],
            ),
            SizedBox(height: 10),
            Text('Seleccione una categoría del menú', style: TextStyle(fontSize: 16, color: Colors.white ))
          ],
        ),  
      ),
    );
  }

}