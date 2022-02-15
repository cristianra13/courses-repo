import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:productos_app/models/categories_model.dart';
import 'package:productos_app/models/models.dart';

class RestaurantProvider extends ChangeNotifier {

  final String _baseUrl = 'wsc.fabricasoftware.co';
  LogoModel? logoModel;
  CategoriesModel? categoriesModel;

  RestaurantProvider() {
    getRestaurantLogo();
    getCategories();
  }

  Future<String> _getJsonData(String path) async {
    final url = Uri.https(_baseUrl, path);
    var response = await http.get(url);
    
    return response.body;
  }

  Future getRestaurantLogo() async {
    
    final jsonData = await _getJsonData('/api/logo/1');
    logoModel = LogoModel.fromJson(jsonData);
    notifyListeners();

    return logoModel;
  }

  Future getCategories() async {
    final jsonData = await _getJsonData('/api/categorias');
    categoriesModel = CategoriesModel.fromJson(jsonData);
    notifyListeners();

    return categoriesModel;
  }

}