import 'package:flutter/material.dart';
import 'package:flutter_barcode_scanner/flutter_barcode_scanner.dart';
import 'package:provider/provider.dart';
import 'package:rq_reader/providers/db_provider.dart';
import 'package:rq_reader/providers/scan_list_provider.dart';
import 'package:rq_reader/utils/utils.dart';

class ScanButton extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return FloatingActionButton(
      elevation: 0,
      child: Icon( Icons.filter_center_focus ),
      onPressed: () async {

        // Recibimos el resultado del escaneo, si cancelamos, recibimos -1
        String barcodeScanRes = await FlutterBarcodeScanner.scanBarcode('#3D8BEF', 'Cancelar', false, ScanMode.QR);
        // String barcodeScanRes = 'geo:4.556380,-74.110691';
        if (barcodeScanRes == '-1') {
          return;
        }
        // Buscamos en el arbol de widgets la instancia de ScanListProvider
        final scanListProvider = Provider.of<ScanListProvider>(context, listen: false);

        final ScanModel nuevoScan = await scanListProvider.nuevoScan(barcodeScanRes);

        launchUr(context, nuevoScan);
      },
    );
  }

}