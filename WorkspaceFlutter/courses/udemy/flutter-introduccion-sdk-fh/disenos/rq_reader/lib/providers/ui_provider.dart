import 'package:flutter/material.dart';

class UiProvider extends ChangeNotifier {

  int _selectedMenuOpt = 0;

  int get selectedMenuOpt {
    return this._selectedMenuOpt;
  }

  set selectedMenuOpt(int i) {
    this._selectedMenuOpt = i;
    // Notificamos a cualquier widget que este escuchando, cuando esto cambie
    notifyListeners();
  }

}