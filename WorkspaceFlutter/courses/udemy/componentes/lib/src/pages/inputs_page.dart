import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class InputPage extends StatefulWidget {
  @override
  _InputPageState createState() => _InputPageState();
}

class _InputPageState extends State<InputPage> {
  // Capturar los datos del imput
  String _nombre = "";
  String _email = "";
  String _password = "";
  String _fecha = '';
  String _opcionSeleccionada = 'Volar';
  List<String> _poderes = [
    'Volar',
    'Super Fuerza',
    'Super velocidad',
    'Rayos X'
  ];

  TextEditingController _inputFieldDateController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Inputs de texto'),
      ),
      body: ListView(
        padding: EdgeInsets.symmetric(horizontal: 10.0, vertical: 20.0),
        children: [
          _crearInput(),
          Divider(),
          _crearEmail(),
          Divider(),
          _crearPassword(),
          Divider(),
          _crearFecha(context),
          Divider(),
          _crearDropDown(),
          Divider(),
          _crearPersona(),
          Divider()
        ],
      ),
    );
  }

  Widget _crearInput() {
    return TextField(
      autofocus: true,
      textCapitalization: TextCapitalization.words,
      decoration: InputDecoration(
          border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(
                  20.0) // le da el borde al cuadro de texto
              ),
          counter: Text('Cantidad de letras ${_nombre.length}'),
          hintText: 'Ingrese su nombre',
          labelText: 'Nombre',
          helperText: 'Solo ingrese su nombre',
          suffixIcon: Icon(Icons.accessibility),
          icon: Icon(Icons.account_circle)),
      onChanged: (valor) {
        setState(() {
          _nombre = valor;
          print(_nombre);
        });
      },
    );
  }

  Widget _crearEmail() {
    return TextField(
        keyboardType: TextInputType.emailAddress,
        decoration: InputDecoration(
            border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(
                    20.0) // le da el borde al cuadro de texto
                ),
            hintText: 'Email',
            labelText: 'Email',
            suffixIcon: Icon(Icons.email),
            icon: Icon(Icons.email)),
        onChanged: (valor) => setState(() {
              _email = valor;
            }));
  }

  Widget _crearPassword() {
    return TextField(
        obscureText: true,
        decoration: InputDecoration(
            border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(
                    20.0) // le da el borde al cuadro de texto
                ),
            hintText: 'Password',
            labelText: 'password',
            suffixIcon: Icon(Icons.lock),
            icon: Icon(Icons.lock)),
        onChanged: (valor) => setState(() {
              _password = valor;
            }));
  }

  Widget _crearFecha(BuildContext context) {
    return TextField(
      enableInteractiveSelection: false,
      // relacionamos el controlador con la propiedad
      controller: _inputFieldDateController,
      decoration: InputDecoration(
          border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(
                  20.0) // le da el borde al cuadro de texto
              ),
          hintText: 'Fecha de nacimiento',
          labelText: 'Fecha de nacimiento',
          suffixIcon: Icon(Icons.perm_contact_calendar),
          icon: Icon(Icons.calendar_today)),
      onTap: () {
        // lanzar selector de fecha
        FocusScope.of(context).requestFocus(new FocusNode());
        _selectDate(context);
      },
    );
  }

  _selectDate(BuildContext context) async {
    DateTime? picked = await showDatePicker(
        context: context,
        initialDate: new DateTime.now(),
        firstDate: new DateTime(2021),
        lastDate: new DateTime(2025),
        locale: Locale('es', 'CO'));

    if (picked != null) {
      // Agregamos la fecha seleccionada a la caja
      setState(() {
        // Damos formato a la fecha
        _fecha = DateFormat('yyyy-MM-dd').format(picked);
        _inputFieldDateController.text = _fecha;
      });
    }
  }

  List<DropdownMenuItem<String>> getOpcionesDropDown() {
    List<DropdownMenuItem<String>> lista = [];

    _poderes.forEach((poder) {
      lista.add(DropdownMenuItem(child: Text(poder), value: poder));
    });

    return lista;
  }

  Widget _crearDropDown() {
    return Row(
      children: [
        Icon(Icons.select_all),
        SizedBox(width: 30.0,),
        Expanded(
          child: DropdownButton<dynamic>(
            value: _opcionSeleccionada,
            items: getOpcionesDropDown(),
            onChanged: (opt) {
              setState(() {
                _opcionSeleccionada = opt;
              });
            },
          )
        )
      ],

    );

    
  }

  _crearPersona() {
    return ListTile(
      title: Text('Nombre es ${_nombre}'),
      subtitle: Text('Email: $_email'),
      trailing: Text(_opcionSeleccionada),
    );
  }
}
