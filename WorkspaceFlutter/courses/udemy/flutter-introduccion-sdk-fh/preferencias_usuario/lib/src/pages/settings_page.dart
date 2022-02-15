import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:preferencias_usuario/src/widgets/menu_widget.dart';
import 'package:preferencias_usuario/src/share_preferences/preferencias_usuario.dart';

class SettingsPage extends StatefulWidget {

  // definimos el nombre de ola página como propiedad static
  static final String routeName = 'settings';

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {

  late bool _colorSecundario;
  late int _genero;
  late String _nombre;

  // Con late le decimos que cuando vaya a usar la variable, está ya deberá tener un valor cargado
  late TextEditingController _textController;
  final PreferenciasUsuario prefs = PreferenciasUsuario(); 

  // Método que se ejecuta cuando se inicaliza este estado, antes del build
  @override
  void initState() {
    super.initState();
    prefs.ultimaPagina = SettingsPage.routeName;
    _genero = prefs.genero;
    _colorSecundario = prefs.colorSecundario;
    _textController = TextEditingController(text: prefs.nombre);
  }

  _setSelectedRadio(int value) {
    prefs.genero = value;
    _genero = value;
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Preferencias de usuario'),
        backgroundColor: (prefs.colorSecundario) ? Colors.teal : Colors.blue,
      ),
      drawer: MenuWidget(),
      body: ListView(
        children: [
          Container(
            child: Text('Settings', style: TextStyle(fontSize: 45.0, fontWeight: FontWeight.bold),),
            padding: EdgeInsets.all(5.0),
          ),
          
          Divider(),

          SwitchListTile(
            value: _colorSecundario,
            title: Text('Color secundario'),
            onChanged: ( value ) {
              setState(() {
                _colorSecundario = value;
                prefs.colorSecundario = value;
              });
            }
          ),

          RadioListTile(
            value: 1,
            title: Text('Masculino'),
            groupValue: _genero,
            onChanged: (value) => _setSelectedRadio(value as int)
          ),

          RadioListTile(
            value: 2,
            title: Text('Femenino'),
            groupValue: _genero,
            onChanged: (value) => _setSelectedRadio(value as int)
          ),

          Divider(),

          Container(
            padding: EdgeInsets.symmetric(horizontal: 20.0),
            child: TextField(
              controller: _textController,
              decoration: InputDecoration(
                labelText: 'Nombre',
                helperText: 'Nombre de la persona usando teléfono'
              ),
              onChanged: ( value ) {
                prefs.nombre = value;
              },
            ),
          )
        ],
      )
    );
  }
}