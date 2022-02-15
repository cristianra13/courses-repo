import 'dart:math';

import 'package:flutter/material.dart';

class Background extends StatelessWidget {

    var boxDecoration = BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
          stops: [0.2, 0.8],
          colors: [
            Color(0xff3d00),
            Color(0xff6633)
          ]
        )
      );

  @override
  Widget build(BuildContext context) {
    
    return Stack(
      children: [
        // Purple gradient
        Container(
          decoration: boxDecoration,
        ),

        // Pink box
        Positioned(
          top: -100,
          left: -30,
          child: _PinkBox()
        )
      ],
    );
  }

}

class _PinkBox extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Transform.rotate(
      angle: -pi / 5,
      child: Container(
        width: 330,
        height: 360,
        decoration: BoxDecoration(
          color: Colors.pink,
          borderRadius: BorderRadius.circular(80),
          gradient: LinearGradient(
            colors: [
              Color.fromRGBO(255, 61, 0, 1),
              Color.fromRGBO(255, 117, 57, 1)
            ]
          )
        ),
      ),
    );
  }
}