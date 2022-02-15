import 'package:flutter/material.dart';

class LoginFormProvider extends ChangeNotifier {

  // Obtenemos la referencia a un formulario (cualquiera que use key: XXXX)
  GlobalKey<FormState> formKey = GlobalKey();

  String name = '';
  String city = '';
  String email = '';
  String password = '';
  bool _isLoading = false;

  bool get isLoading => _isLoading;
  set isLoading(bool value) {
    _isLoading = value;
    notifyListeners();
  }
  
  bool isValidForm() {
    print(formKey.currentState?.validate());
    return formKey.currentState?.validate() ?? false;
  }

}