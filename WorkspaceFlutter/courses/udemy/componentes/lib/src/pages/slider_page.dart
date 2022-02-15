import 'package:flutter/material.dart';

class SliderPage extends StatefulWidget {
  @override
  _SliderPageState createState() => _SliderPageState();
}

class _SliderPageState extends State<SliderPage> {
  double _valorSlider = 100.0;
  bool _bloquearCheck = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Sliders'),
      ),
      body: Container(
          padding: EdgeInsets.only(top: 50.0),
          child: Column(
            children: [
              _crearSlider(),
              _crearCheckbox(),
              _crearSwitch(),
              Expanded(
                child: _crearImagen(),
              )
            ],
          )),
    );
  }

  Widget _crearSlider() {
    return Slider(
      activeColor: Colors.indigoAccent,
      label: 'Tamaño de la imagen',
      // divisions: 20,
      value: _valorSlider,
      min: 100.0,
      max: 400.0,
      // Recibe el valor del slider
      onChanged: (_bloquearCheck)
          ? null
          : (valor) {
              setState(() {
                _valorSlider = valor;
              });
            },
    );
  }

  Widget _crearImagen() {
    return Image(
      image: NetworkImage(
          'https://static.wikia.nocookie.net/wiki-de-rick-morty/images/5/53/Rick_y_morty.png/revision/latest?cb=20170331115948&path-prefix=es'),
      width: _valorSlider,
      fit: BoxFit.contain,
    );
  }

  Widget _crearCheckbox() {
    return CheckboxListTile(
      title: Text('Bloquear slider'),
      value: _bloquearCheck,
      onChanged: (valor) {
        setState(() {
          _bloquearCheck = valor!;
        });
      },
      
    );

    // return Checkbox(
    //   value: _bloquearCheck,
    //   onChanged: (valor) {
    //     setState(() {
    //       _bloquearCheck = valor!;
    //     });
    //   },
    // );
  }

  Widget _crearSwitch() {

    return SwitchListTile(
      title: Text('Bloquear slider'),
      value: _bloquearCheck,
      onChanged: (valor) {
        setState(() {
          _bloquearCheck = valor!;
        });
      },
      
    );

  }
}
