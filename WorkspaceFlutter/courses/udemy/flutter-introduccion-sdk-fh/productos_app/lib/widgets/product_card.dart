import 'package:flutter/material.dart';
import 'package:productos_app/models/product.dart';

class ProductCardWidget extends StatelessWidget {

  final Product product;

  const ProductCardWidget({
    Key? key, 
    required this.product
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: 20),
      child: Container(
        margin: const EdgeInsets.only(top: 30, bottom: 50),
        width: double.infinity,  
        height: 400,
        decoration: _cardBorders(),
        child: Stack(
          alignment: Alignment.bottomLeft,
          children: [
            _BackgroundImage(product: product,),

            _ProductDetails(product: product,),

            Positioned(
              child: _PriceTag(product: product,),
              top: 0,
              right: 0,
            ),

            if (!product.available)
              Positioned(
                child: _NotAvailable(product: product,),
                top: 0,
                left: 0,
              )
          ],
        ),
      ),
    );
  }

  BoxDecoration _cardBorders() => BoxDecoration(
    color: Colors.white,
    borderRadius: BorderRadius.circular(25),
    boxShadow: [
      BoxShadow(
        color: Colors.black12,
        offset: Offset(0,7),
        blurRadius: 10
      )
    ]
  );
}

class _BackgroundImage extends StatelessWidget {

  final Product product;

  const _BackgroundImage({
    Key? key, 
    required this.product
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return ClipRRect(
      borderRadius: BorderRadius.circular(25), // redondeamos la imagen
      child: Container(
        width: double.infinity,
        height: 400,
        child: product.picture == null
        ? Image(image: AssetImage('assets/no-image.png'), fit: BoxFit.cover)
        : FadeInImage(
            placeholder: AssetImage('assets/jar-loading.gif'), // mientras carga la imagen
            image: NetworkImage(product.picture!),
            fit: BoxFit.cover // expandimos la imagen
        ),
      ),
    );
  }
}

class _ProductDetails extends StatelessWidget {

  final Product product;

  const _ProductDetails({
    Key? key, 
    required this.product
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Padding(
      padding: EdgeInsets.only(right: 50),
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
        width: double.infinity,
        height: 70,
        decoration: _buildBoxDecoration(),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              product.name,
              style: TextStyle(fontSize: 20, color: Colors.white, fontWeight: FontWeight.bold),
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
            ),

            Text(
              product.id!,
              style: TextStyle(fontSize: 15, color: Colors.white)
            ),
          ],
        ),
      ),
    );

  }

  BoxDecoration _buildBoxDecoration() => BoxDecoration(
    color: Color.fromRGBO(255, 57, 17, 1),
    borderRadius: BorderRadius.only(bottomLeft: Radius.circular(25), topRight: Radius.circular(25))
  );
}

class _PriceTag extends StatelessWidget {

  final Product product;

  const _PriceTag({
    Key? key, 
    required this.product
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Container(
      alignment: Alignment.center,
      child: FittedBox(
        fit: BoxFit.contain,
        child: Padding(
          padding: EdgeInsets.symmetric(horizontal: 10),
          child: Text(
            '\$${product.price}',
            style: TextStyle( color: Colors.white, fontSize: 20),
          ),
        ),
      ),
      width: 100,
      height: 70,
      decoration: BoxDecoration(
        color: Color.fromRGBO(255, 57, 17, 1),
        borderRadius: BorderRadius.only(topRight: Radius.circular(25), bottomLeft: Radius.circular(25))
      ),
    );
  }
}

class _NotAvailable extends StatelessWidget {

  final Product product;

  const _NotAvailable({
    Key? key, 
    required this.product
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Container(
      child: FittedBox(
        fit: BoxFit.contain,
        child: Padding(
          padding: EdgeInsets.symmetric(horizontal: 10),
          child: Text(
            'No disponible',
            style: TextStyle(color: Colors.white, fontSize: 20),
          ),
        ),
      ),
      width: 100,
      height: 70,
      decoration: BoxDecoration(
        color: Colors.yellow[800],
        borderRadius: BorderRadius.only(topLeft: Radius.circular(25), bottomRight: Radius.circular(25))
      ),
    );
  }
}