import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rq_reader/providers/ui_provider.dart';

class CustomNavigatorBar extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    // Obtener el _selectedMenuOpt del context
    final uiprovider = Provider.of<UiProvider>(context);
    final currentIndex = uiprovider.selectedMenuOpt;

    return BottomNavigationBar(
      onTap: (int i) => uiprovider.selectedMenuOpt = i,
      currentIndex: currentIndex,
      elevation: 0,
      items: const [
        BottomNavigationBarItem(
          icon: Icon(Icons.map),
          label: 'Mapa'
        ),
        
        BottomNavigationBarItem(
          icon: Icon(Icons.compass_calibration),
          label: 'Direcciones'
        )
      ],
    );
  }
}