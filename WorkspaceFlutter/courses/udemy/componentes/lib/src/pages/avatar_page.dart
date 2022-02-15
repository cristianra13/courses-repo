import 'package:flutter/material.dart';

class AvatarPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    
    return Scaffold(
      appBar: AppBar(
        title: Text('Avatar page'),
        actions: [
          Container(
            padding: EdgeInsets.all(5.0),
            child: CircleAvatar(
              backgroundImage: NetworkImage('https://www.latercera.com/resizer/BaNHXVW24tLkG2DTrioBtuWyCxA=/900x600/smart/arc-anglerfish-arc2-prod-copesa.s3.amazonaws.com/public/WPNKFC3FPBBJ3NJK5PINB2XPUM.png'),
              radius: 25.0,
            ),
          ),

          Container(
            margin: EdgeInsets.only(right: 10.0),
            child: CircleAvatar(
              child: Text('RS'),
              backgroundColor: Colors.brown,
            ),
          )
        ],
      ),
      body: Center(
        child: FadeInImage(
          image: NetworkImage('https://elcomercio.pe/resizer/oZImoPbGORWHZLuaI9gXnRUhRws=/1200x900/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/EG372BHUU5E25F7OYXFUZ362GM.jpg'),
          placeholder: AssetImage('assets/jar-loading.gif'),
          fadeInDuration: Duration(milliseconds: 200),
        ),
      ),
    );
  }
  

}