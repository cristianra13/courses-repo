import 'dart:ui';

import 'package:flutter/material.dart';

class CardTable extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Table(
      children: [
        // Es importante saber que siempre se deben enviar la misma cantidad de elementos por cada TableRow creado
        TableRow(
          children: [
            _SingleCard(color: Colors.blue, icon: Icons.border_all, text: 'General'),
            _SingleCard(color: Colors.pinkAccent, icon: Icons.car_rental, text: 'Transport'),
          ]
        ),
        TableRow(
          children: [
            _SingleCard(color: Colors.purple, icon: Icons.shopping_bag_outlined, text: 'Shopping'),
            _SingleCard(color: Colors.red, icon: Icons.cloud_download_sharp, text: 'Downloads'),
          ]
        ),

        TableRow(
          children: [
            _SingleCard(color: Colors.purpleAccent, icon: Icons.cloud, text: 'Cloud'),
            _SingleCard(color: Colors.cyanAccent, icon: Icons.airplane_ticket_outlined, text: 'Trips'),
          ]
        ),

        TableRow(
          children: [
            _SingleCard(color: Colors.green, icon: Icons.local_grocery_store, text: 'Grocery'),
            _SingleCard(color: Colors.limeAccent, icon: Icons.movie_creation_outlined, text: 'Movies'),
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

// Se enfoca en la construcción del widget interno de la tarjeta
    return _Cardbackground(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          CircleAvatar(
            backgroundColor: this.color,
            child: Icon(this.icon, size: 35, color: Colors.white,),
            radius: 30,
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
