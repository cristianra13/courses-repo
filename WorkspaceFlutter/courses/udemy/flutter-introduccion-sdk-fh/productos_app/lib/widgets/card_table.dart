import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:productos_app/providers/restaurant_provider.dart';
import 'package:productos_app/screens/products_cart_page.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';

class CardTable extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    final ProductService productService = Provider.of<ProductService>(context);
    final restaurantService = Provider.of<RestaurantProvider>(context);

    return Table(
      children: [
        // Es importante saber que siempre se deben enviar la misma cantidad de elementos por cada TableRow creado
        TableRow(
          children: [
            GestureDetector(
              child: _SingleCard(color: Colors.blue, icon: Icons.food_bank_outlined, text: 'Especialidades',),
                onTap: () {
                  Navigator.pushNamed(context, HomeScreen.id);
                },
              ),
            _SingleCard(color: Colors.pinkAccent, icon: Icons.breakfast_dining_outlined, 
              text: restaurantService.categoriesModel!.datos[0].nombre),
          ]
        ),
        TableRow(
          children: [
            _SingleCard(color: Colors.orangeAccent, icon: Icons.ramen_dining_outlined, 
              text: restaurantService.categoriesModel!.datos[1].nombre),
            _SingleCard(color: Colors.red, icon: Icons.dinner_dining_outlined, 
              text: restaurantService.categoriesModel!.datos[2].nombre),
          ]
        ),

        TableRow(
          children: [
            _SingleCard(color: Colors.pink, icon: Icons.liquor_outlined, 
              text: restaurantService.categoriesModel!.datos[3].nombre),
            GestureDetector(
              child: _SingleCard(color: Colors.green, icon: Icons.local_grocery_store, text: 'Carrito'),
              onTap: () {
                if (productService.productsInCart.length == 0) {
                  NotificationsService.showSnackbar("No hay artículos en el carrito!");
                  return;
                }

                Navigator.pushNamed(context, ProductsCartScreen.id);

              },
            ),
          ]
        ),
      ],
    );
    
  }
}

class _SingleCard extends StatelessWidget {

  final IconData icon;
  final Color color;
  final String text;

  const _SingleCard({
    Key? key, 
    required this.icon,
    required this.color,
    required this.text
    }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    
    final productService = Provider.of<ProductService>(context);

    Widget counterArt() {
      if (productService.productsInCart.length > 0) {
        return Container(
          height: 20,
          width: 20,
          decoration: BoxDecoration(
            color: Colors.red,
            borderRadius: BorderRadius.circular(50)
          )
        );
      } else {
        return Container();
      }
    }

// Se enfoca en la construcción del widget interno de la tarjeta
    return _Cardbackground(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [

          Stack(
            children: [
              
              CircleAvatar(
                backgroundColor: this.color,
                child: Icon(this.icon, size: 35, color: Colors.white,),
                radius: 30,
              ),
            ],
          ),
          SizedBox(
            height: 10,
          ),

          Text(this.text, style: TextStyle(color: this.color, fontSize: 18),)
        ],
      )
    );

  }
}

class _Cardbackground extends StatelessWidget {

  final Widget child;

  const _Cardbackground({
    Key? key, 
    required this.child
    }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    // Damos el difuminado del fondo de las tarjetas
    return Container(
      margin: EdgeInsets.all(15),
      // ClipRRect permite que los efectos no se expandan por todo el dispositivo
      child: ClipRRect(
        borderRadius: BorderRadius.circular(20),
        child: BackdropFilter(
          filter: ImageFilter.blur( sigmaX: 5, sigmaY: 5 ),
          child: Container(
            height: 180,
            decoration: BoxDecoration(
              color: Color.fromRGBO(62, 67, 107, 0.7),
              borderRadius: BorderRadius.circular(20)
            ),
        
            child: this.child
        
          ),
        ),
      ),
    );

  }

}
