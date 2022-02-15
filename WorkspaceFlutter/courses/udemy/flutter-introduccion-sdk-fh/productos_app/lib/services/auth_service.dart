import 'dart:convert';

// import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
// import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:http/http.dart' as http;
import 'package:productos_app/models/models.dart';
import 'package:productos_app/utils/encrypt_data.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class AuthService extends ChangeNotifier {
  final String _baseUrl = 'identitytoolkit.googleapis.com';
  final String _firebaseToken = 'AIzaSyCZhQVc7DG0tYwmB_yv5kdMk5oXkxLQWJg';
  final String _baseDbUrl = 'flutter-varios-2069a-default-rtdb.firebaseio.com';

  // storage es para almacenamiento seguro del telefono
  final storage = FlutterSecureStorage();
  final _firestore = FirebaseFirestore.instance;
  //CollectionReference users = FirebaseFirestore.instance.collection('users');

  // Si retornamos algo, es porque es un error, si no, todo está bien
  Future<String?> createUser(
      String name, String city, String email, String password) async {
    final Map<String, dynamic> authData = {
      'name': name,
      'city': city,
      'email': email,
      'password': password,
      'returnSecureToken': true
    };

    // creamos el url
    final url = Uri.https(_baseUrl, '/v1/accounts:signUp', {
      'key': _firebaseToken // Este es un header
    });

    // disparamos la petición
    final response = await http.post(url, body: json.encode(authData));
    final Map<String, dynamic> decodedResp = json.decode(response.body);
    print(decodedResp);

    if (decodedResp.containsKey('idToken')) {
      // grabamos el token en el secure storage
      storage.write(key: 'token', value: decodedResp['idToken']);

      UserRegisterModel userModel = UserRegisterModel(
          name: name, city: city, email: email, password: password);

      await registerUserOnDb(userModel);
      return null;
    } else {
      if (decodedResp['error']['message'] == 'EMAIL_EXISTS')
        return 'Ya hay un registro con el email: $email';
      return 'Error al crear usuario';
    }
  }

  Future<String?> login(String email, String password) async {
    final Map<String, dynamic> authData = {
      'email': email,
      'password': password,
      'returnSecureToken': true
    };

    // creamos el url
    final url = Uri.https(_baseUrl, '/v1/accounts:signInWithPassword', {
      'key': _firebaseToken // Esto es un header
    });

    // disparamos la petición
    final response = await http.post(url, body: json.encode(authData));
    final Map<String, dynamic> decodedResp = json.decode(response.body);

    if (decodedResp.containsKey('idToken')) {
      // grabamos el token en el secure storage
      storage.write(key: 'token', value: decodedResp['idToken']);
      return null;
    } else {
      return 'Usuario o contraseña invalidos';
    }
  }

  Future logout() async {
    await storage.delete(key: 'token');
    return;
  }

  // Validamos si tenemos un token
  Future<String> readToken() async {
    return await storage.read(key: 'token') ?? 'no-token';
  }

  Future registerUserOnDb(UserRegisterModel userModel) async {
    // print(await storage.read(key: 'token'));
    try {
      userModel.password = EncryptData.encryptAES(userModel.password);

      final response = await _firestore.collection('users').add({
        'name': userModel.name,
        'city': userModel.city,
        'email': userModel.email,
        'password': userModel.password
      });
    } catch (e) {
      throw e;
    }
    // print('response from firebase: $response');
    // response.get().then((value) => {
    //   value.data()!.forEach((key, value) {
    //     print('$key --- $value');
    //   })
    // });

    // final url = Uri.https(_baseDbUrl, 'users.json');
    // // final response = await http.post(url);
    // final response = await http.post(url, body: userModel.toJson());
    // final decodedData = json.decode(response.body);
    // print(decodedData);
  }
}
