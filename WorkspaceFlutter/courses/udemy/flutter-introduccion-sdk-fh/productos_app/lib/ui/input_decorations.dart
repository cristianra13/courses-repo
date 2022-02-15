import 'package:flutter/material.dart';

class InputDecorations {

  

  static InputDecoration authinputDecoration({
    required String hintText,
    required String labelText,
    IconData? prefixIcon
  }) {

    Color color = Color.fromRGBO(255, 57, 17, 1);
    
    return InputDecoration(
      enabledBorder: UnderlineInputBorder(
        borderSide: BorderSide(color: color)
      ),

      focusedBorder: UnderlineInputBorder(
        borderSide: BorderSide(
          color: color,
          width: 2
        )
      ),

      hintText: hintText,
      labelText: labelText,
      labelStyle: TextStyle(
        color: Colors.grey
      ),

      prefixIcon: prefixIcon != null
      ? Icon( prefixIcon, color: color )
      : null
    );
  }

}