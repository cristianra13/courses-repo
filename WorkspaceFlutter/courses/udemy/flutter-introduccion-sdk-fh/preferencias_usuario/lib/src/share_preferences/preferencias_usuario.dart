import 'package:shared_preferences/shared_preferences.dart';

// Clase Singleton
class PreferenciasUsuario {

  static final PreferenciasUsuario _instancia = PreferenciasUsuario._internal();

  factory PreferenciasUsuario() {
    return _instancia;
  }

  PreferenciasUsuario._internal();
  late SharedPreferences _prefs;

  initPrefs() async {
    _prefs = await SharedPreferences.getInstance();
  }

  // GET y SET genero
  int get genero {
    return _prefs.getInt('genero') ?? 1;
  }

  set genero(int value) {
    _prefs.setInt('genero', value);
  }

  // GET y SET colorSecundario
  bool get colorSecundario {
    return _prefs.getBool('colorSecundario') ?? false;
  }

  set colorSecundario(bool value) {
    _prefs.setBool('colorSecundario', value);
  }

  // GET y SET nombre
  String get nombre {
    return _prefs.getString('nombre') ?? '';
  }

  set nombre (String value) {
    _prefs.setString('nombre', value);
  }

  // GET y SET última Pagina
  String get ultimaPagina {
    return _prefs.getString('ultimaPagina') ?? 'home';
  }

  set ultimaPagina (String value) {
    _prefs.setString('ultimaPagina', value);
  }
}
