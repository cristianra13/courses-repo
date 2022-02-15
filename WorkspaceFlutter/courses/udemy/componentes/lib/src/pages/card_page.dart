import 'package:flutter/material.dart';

class CardPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Cards'),
      ),
      body: ListView(padding: EdgeInsets.all(10.0), children: [
        _cardTipoUno(),
        SizedBox(height: 30.0),
        _cardTipoDos(),
        SizedBox(height: 30.0),
        _cardTipoUno(),
        SizedBox(height: 30.0),
        _cardTipoDos(),
        SizedBox(height: 30.0),
        _cardTipoUno(),
        SizedBox(height: 30.0),
        _cardTipoDos(),
        SizedBox(height: 30.0),
      ]),
    );
  }

  Widget _cardTipoUno() {
    return Card(
      // está propiedad da la sombra
      elevation: 10.0,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      child: Column(
        children: [
          ListTile(
            leading: Icon(
              Icons.photo_album,
              color: Colors.blue,
            ),
            title: Text('Título de esta tarjeta'),
            subtitle: Text(
                'Descriocion de la tarjeta de lo que se quiere hacer dsfdsvfnlkdsngkjdsngkdsj'),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              TextButton(
                child: Text('Cancelar'),
                onPressed: () {},
              ),
              TextButton(
                child: Text('OK'),
                onPressed: () {},
              )
            ],
          )
        ],
      ),
    );
  }

  _cardTipoDos() {
    // tarjeta con imagen
    final card = Container(
      child: Column(
        children: [
          // Widget para trabajar el loading de las imagenes
          FadeInImage(
            image: NetworkImage(
                'https://cdn.wallpapersafari.com/57/20/oL3Uc7.jpg'),
            placeholder: AssetImage('assets/jar-loading.gif'),
            fadeInDuration: Duration(milliseconds: 200),
            height: 250.0,
            fit: BoxFit.cover,
          ),

          // Image(
          //   image: NetworkImage('https://cdn.wallpapersafari.com/57/20/oL3Uc7.jpg'),
          // ),
          Container(
              padding: EdgeInsets.all(10.0),
              child: Text('No tengo ide de que poner'))
        ],
      ),
    );

    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(30.0),
        color: Colors.white,
        boxShadow: <BoxShadow> [
          BoxShadow(
            color: Colors.black26,
            blurRadius: 10.0,
            spreadRadius: 2.0,
            offset: Offset(2.0, 10.0)
          )
        ]
      ),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(30.0),
        child: card,
      ),

    );
  }
}
