import 'package:flutter/material.dart';
import 'package:rq_reader/widgets/scan_tiles.dart';


class MapasPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return const ScanTiles(tipo: 'geo');

  }
}