
/**
 * Utilidad para devolver un icono según el nombre que se reciba
 */
import 'package:flutter/material.dart';

final _icons = <String, IconData>{
  'add_alert': Icons.add_alert,
  'accessibility': Icons.accessibility,
  'folder_open': Icons.folder_open,
  'donut_large': Icons.donut_large,
  'input': Icons.input,
  'tune': Icons.tune,
  'list': Icons.list,
};

Icon geticon(String nombreIcono) {
  return Icon( _icons[nombreIcono], color: Colors.blue, );
}
