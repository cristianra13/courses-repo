import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rq_reader/pages/direcciones_page.dart';
import 'package:rq_reader/pages/mapas_page.dart';
import 'package:rq_reader/providers/scan_list_provider.dart';
import 'package:rq_reader/providers/ui_provider.dart';
import 'package:rq_reader/widgets/custom_navigator_bar.dart';
import 'package:rq_reader/widgets/scan_button.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        title: Text('Historial'),
        actions: [
          IconButton(
            icon: Icon(Icons.delete_forever),
            onPressed: () {
              Provider.of<ScanListProvider>(context, listen: false).borrarTodosScans();
            },
          )
        ],
      ),
      body: _HomePagebody(),
      bottomNavigationBar: CustomNavigatorBar(),
      floatingActionButton: ScanButton(),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }
}

class _HomePagebody extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Obtener el _selectedMenuOpt del context
    final uiprovider = Provider.of<UiProvider>(context);
    final int currentIndex = uiprovider.selectedMenuOpt;

    // Usar scanListProvider
    final scanListProvider =
        Provider.of<ScanListProvider>(context, listen: false);

    switch (currentIndex) {
      case 0:
        scanListProvider.cargarScansPorTipo('geo');
        return MapasPage();
      case 1:
        scanListProvider.cargarScansPorTipo('http');
        return DireccionesPage();
      default:
        return MapasPage();
    }
  }
}
