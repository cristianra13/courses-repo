import 'package:flutter/material.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';


final Color color = Color.fromRGBO(255, 57, 17, 1);

class ProductsCartScreen extends StatefulWidget {

  static final String id = 'products_cart_screen';

  @override
  State<ProductsCartScreen> createState() => _ProductsCartScreenState();
}

class _ProductsCartScreenState extends State<ProductsCartScreen> {

  @override
  Widget build(BuildContext context) {

    final ProductService productService = Provider.of<ProductService>(context);
    final authService = Provider.of<AuthService>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text('Carrito', style: TextStyle(fontSize: 30),),
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
        itemCount: productService.productsInCart.length,
        itemBuilder: (BuildContext context,int index) {
          final product = productService.productsInCart[index];

          return Row(
            children: [
              SizedBox(
                width: 70,
                child: AspectRatio(
                  aspectRatio: 0.88,
                  child: Container(
                    padding: EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      color: Color(0xFFF5F6F9),
                      borderRadius: BorderRadius.circular(15)
                    ),
                    child: Image.network(product.picture!),
                  ),
                ),
              ),
              SizedBox(
                width: 30,
              ),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    product.name,
                    style: TextStyle(
                      fontSize: 20
                    ),
                  ),
                  SizedBox(height: 7,),
                  Text.rich(
                    TextSpan(
                      text: "\$${product.price.toString()}",
                      style: TextStyle(fontWeight: FontWeight.w900, color: color),
                      children: [
                        TextSpan(text: " x2")
                      ]
                    )
                  )
                ],
              )
            ],
          );
        }
      ),

      bottomNavigationBar: BottomNavigationPayMenu(color: color)
   );
  }
}

class BottomNavigationPayMenu extends StatelessWidget {

  const BottomNavigationPayMenu({
    Key? key,
    required this.color,
  }) : super(key: key);

  final Color color;

  @override
  Widget build(BuildContext context) {

    return Container(
      padding: EdgeInsets.symmetric(vertical: 15, horizontal: 15),
      height: 170,
      decoration: BoxDecoration(
        color: color,
        borderRadius: BorderRadius.only(topLeft: Radius.circular(30), topRight: Radius.circular(30)),
        boxShadow: [
          BoxShadow(
            offset: Offset(0,-15),
            blurRadius: 20,
            color: Color(0xFFDADADA).withOpacity(0.15)
          ),  
        ]
      ),
      child: Column(
        children: [
          Row(
            children: [
              Container(
                height: 40,
                width: 40,
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(10)
                ),
                child: Icon(Icons.payment_outlined, size: 35,),
              )
            ],
          ),
          SizedBox(height: 10,),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text.rich(
                TextSpan(
                  text: "Total: ",
                  style: TextStyle(
                    fontSize: 18,
                    color: Colors.white
                  ),
                  children: [
                    TextSpan(
                    text: "\$125.000", 
                      style: TextStyle(
                        fontSize: 20, 
                        fontWeight: FontWeight.bold, 
                        color: Colors.white)
                    )
                  ]
                )
              ),
              GestureDetector(
                child: Container(
                  width: 150,
                  height: 60,
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(25),
                  ),
                  child: Center(
                    child: Text(
                      'PAGAR',
                      style: TextStyle(
                        fontSize: 25,
                        fontWeight: FontWeight.bold,
                        color: color
                      ),
                    ),
                  ),
                ),
                onTap: () {
                  print('tap to pay');
                },
              ),
              // SizedBox(
              //   height: 15,
              // ),

            ],
          )
        ],
      ),
    );
  }
}