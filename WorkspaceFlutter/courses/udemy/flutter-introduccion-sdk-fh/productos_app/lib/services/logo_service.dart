import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:productos_app/models/models.dart';

class LogoService extends ChangeNotifier {
    
  final String _baseUrl = 'wsc.fabricasoftware.co';

  LogoService() {

  }

  Future<String> getJsonData() async {
    final url = Uri.https(_baseUrl, '/api/logo/1');
    var response = await http.get(url);
    final LogoModel urlLogo = LogoModel.fromJson(response.body);
    notifyListeners();
    
    return urlLogo.urlLogo;
  }
}