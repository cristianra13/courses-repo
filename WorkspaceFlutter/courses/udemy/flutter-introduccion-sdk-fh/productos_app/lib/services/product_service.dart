import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:productos_app/models/product.dart';
import 'package:http/http.dart' as http;

class ProductService extends ChangeNotifier {
  final String _baseUrl = 'flutter-varios-2069a-default-rtdb.firebaseio.com';
  final List<Product> products = [];
  final List<Product> productsInCart = [];
  late Product selectedProduct;
  bool isLoading = true;
  bool isSaving = false;
  File? newPictureFile;

  final storage = FlutterSecureStorage();

  ProductService() {
    loadProducts();
  }

// <List<Product>>
  Future loadProducts() async {
    isLoading = true;
    notifyListeners();

    final url = Uri.https(_baseUrl, 'products.json', {
      'auth': await storage.read(key: 'token') ?? ''
    });
    final response = await http.get(url);

    final Map<String, dynamic> productsMap = json.decode(response.body);
    productsMap.forEach((key, value) {
      final tempProduct = Product.fromMap(value);
      tempProduct.id = key;
      products.add(tempProduct);
    });

    isLoading = false;
    notifyListeners();

    return products;
  }

  Future saveOrCreateProduct(Product product) async {
    isSaving = true;
    notifyListeners();

    if (product.id == null) {
      // Es necesario crear
      await createProduct(product);
    } else {
      // Actualizar
      await updateProduct(product);
    }

    isSaving = false;
    notifyListeners();
  }

  Future<String> updateProduct(Product product) async {
    // Hacemos la petición al backend
    final url = Uri.https(_baseUrl, 'products/${product.id}.json');
    final response = await http.put(url, body: product.toJson());
    final decodedData = response.body;

    // Regresa el indice
    final index = products.indexWhere((element) => element.id == product.id);
    products[index] = product;

    return product.id!;
  }

  Future<String> createProduct(Product product) async {
    // Hacemos la petición al backend
    final url = Uri.https(_baseUrl, 'products.json');
    final response = await http.post(url, body: product.toJson());
    final decodedData = json.decode(response.body);
    product.id = decodedData['name'];
    // products.add(product);

    return product.id!;
  }

  void updateSelectedProductImage(String path) {
    // tenemos el archivo en newPictureFile
    selectedProduct.picture = path;
    newPictureFile = File.fromUri(Uri(path: path));
    notifyListeners();
  }

  Future<String?> uploadImage() async {
    if (newPictureFile == null) return null;
    isSaving = true;
    notifyListeners();

    final url = Uri.parse(
        'https://api.cloudinary.com/v1_1/cristianreal-org/image/upload?upload_preset=xdhcn86z');
    // creamos el request y asignamos la imagen
    final imageUploadRequest = http.MultipartRequest('POST', url);
    final file = await http.MultipartFile.fromPath('file', newPictureFile!.path);
    
    imageUploadRequest.files.add(file);

    final streamResponse = await imageUploadRequest.send();
    final resp = await http.Response.fromStream(streamResponse);
    

    if (resp.statusCode != 200 && resp.statusCode != 201) {
      print('Algo salio mal');
      print(resp.body);
      return null;
    }
    // limpiamos la imagen
    newPictureFile = null;
    final decodedData = json.decode(resp.body);

    // Retornamos la url de la imagen 
    return decodedData['secure_url'];
  }
}
