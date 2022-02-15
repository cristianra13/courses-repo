import 'package:flutter/material.dart';

class BasicDesignScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Column(
      children: [
        Image(
          // Imagen superior
          image: AssetImage('assets/landscape.jpg'),
        ),

        // Título
        Title(),

        _ButtonSection(),

        // Descripción
        Container(
          margin: EdgeInsets.symmetric(horizontal: 20, vertical: 20),
          child: Text(
            'Sint veniam enim deserunt commodo minim incididunt eu non proident amet pariatur Lorem irure sit. Sint est dolor laborum aliqua fugiat adipisicing. Sint ut in sint exercitation do esse officia sint. Fugiat irure occaecat cupidatat minim aliquip consequat magna fugiat nostrud. Consequat laboris aute nulla nisi non est pariatur.',
            textAlign: TextAlign.justify
          )
        )
      ],
    ));
  }
}

class Title extends StatelessWidget {
  const Title({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 30, vertical: 10),
      child: Row(
        children: [
          Column(
            // Alineamos de forma Horizontal
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text('Oeschine Lake Campground',
                  style: TextStyle(fontWeight: FontWeight.bold)),
              Text(
                'Kanderste, Switzerland',
                style: TextStyle(color: Colors.black45),
              ),
            ],
          ),
          Expanded(child: Container()),
          Icon(
            Icons.star,
            color: Colors.red,
          ),
          Text('41'),
        ],
      ),
    );
  }
}

class _ButtonSection extends StatelessWidget {
  const _ButtonSection({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 30, vertical: 10),
      child: Container(
        margin: EdgeInsets.symmetric(horizontal: 40),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            CustomButtom(icon: Icons.call, text: 'CALL',),
            CustomButtom(icon: Icons.map_sharp, text: 'ROUTE'),
            CustomButtom(icon: Icons.share, text: 'SHARE'),
          ],
        ),
      ),
    );
  }
}

class CustomButtom extends StatelessWidget {
  final IconData icon;
  final String text;
  

  const CustomButtom({
    Key? key, required this.icon, required this.text,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Icon(
          this.icon,
          color: Colors.blue,
          size: 30,
        ),
        Text(
          this.text,
          style: TextStyle(color: Colors.blue),
        )
      ],
    );
  }
}
