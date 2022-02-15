import 'package:flutter/material.dart';
import 'package:productos_app/models/models.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:productos_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {

  static const String id = 'home_screen';

  @override
  Widget build(BuildContext context) {

    final ProductService productService = Provider.of<ProductService>(context);
    final authService = Provider.of<AuthService>(context, listen: false);

    if (productService.isLoading) return LoadingScreen();

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text('Productos', style: TextStyle(fontSize: 30),),
        backgroundColor: Color.fromRGBO(255, 57, 17, 1),
        actions: [
          IconButton(
            icon: Icon(Icons.login_outlined),
            onPressed: () {
              authService.logout();
              Navigator.pushReplacementNamed(context, LoginScreen.id);
            },
          ),
        ],
      ),

      body: ListView.builder(
          itemCount: productService.products.length,
          itemBuilder: (BuildContext context, int index) => GestureDetector(
              onTap: () {
                productService.selectedProduct = productService.products[index].copy();
                Navigator.pushNamed(context, ProductScreen.id);
              },
              child: ProductCardWidget(product: productService.products[index])
          )
      ),

      // floatingActionButton: FloatingActionButton(
      //   child: Icon(Icons.add),
      //   onPressed: () {
      //     productService.selectedProduct = Product(
      //       available: false,
      //       name: '',
      //       price: 0.0
      //     );
      //     Navigator.pushNamed(context, ProductScreen.id);
      //   },
      // ),
    );
  }
}
