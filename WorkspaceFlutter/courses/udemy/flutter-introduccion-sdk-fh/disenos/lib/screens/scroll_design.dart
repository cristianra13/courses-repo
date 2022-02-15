import 'package:flutter/material.dart';


class ScrollDesignScreen extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    
    final BoxDecoration boxDecoration = BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            stops: [0.5, 0.5],
            colors: [
              Color(0xff5EE8C5),
              Color(0xff30BAD6)
            ]
          )
        );

    


    return Scaffold(
      // Implementamos un gradient
      backgroundColor: Color(0xff30BAD6),
      body: Container(
        decoration: boxDecoration,

        child: PageView(
          // Para hacer scroll verticalmente
          scrollDirection: Axis.vertical,
          // physics: BouncingScrollPhysics(), // Hacer el scroll del final como en ios
          children: [
            Page1(),
            Page2()
          ],
        ),
      )
   );

  }
}

class Page1 extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Stack(
      children: [
        // Imagen de fondo
        Background(),

        MainContent()

      ],
    );
  }
}

class Background extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Color(0xff30BAD6),
      // decimos que tome todo el espcacio posible
      height: double.infinity,
      alignment: Alignment.topCenter,
      child: Image(
        image: AssetImage('assets/scroll-1.png')
      ),

    );
  }
}

class MainContent extends StatelessWidget {



  const MainContent({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {

    final TextStyle textStyle = TextStyle(fontSize: 60, fontWeight: FontWeight.bold, color: Colors.white);

    return SafeArea(
      bottom: false,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(height: 30,),
          Text('11°', style: textStyle,),
          Text('Miércoles', style: textStyle,),
          
          // Se expande en todo el expacio posible
          Expanded(child: Container()),
    
          Icon(Icons.keyboard_arrow_down, size: 100, color: Colors.white,)
        ],
    
      ),
    );
  }
}

class Page2 extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Color(0xff30BAD6),
      child: Center(
        child: TextButton(
          onPressed: () {},
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 40),
            child: Text('Bienvenido', style: TextStyle(color: Colors.white, fontSize: 30),),
          ),
          style: TextButton.styleFrom(
            backgroundColor: Color(0xff0098FA),
            shape: StadiumBorder()
          ),
        ),
      ),
    );
  }
}