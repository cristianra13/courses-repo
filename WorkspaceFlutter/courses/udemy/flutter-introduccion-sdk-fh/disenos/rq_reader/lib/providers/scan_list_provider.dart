import 'package:flutter/material.dart';
import 'package:rq_reader/providers/db_provider.dart';


class ScanListProvider extends ChangeNotifier {

    List<ScanModel> scans = [];
    String tipoSeleccionado = 'http';

    Future<ScanModel> nuevoScan(String valor) async{
      final nuevoScan = ScanModel(valor: valor);
      final id = await DBProvider.dbProvider.nuevoScan(nuevoScan);
      // asigbamos el id de la bd al modelo
      nuevoScan.id  = id;
      // validamos para mostralo en pantalla
      if (tipoSeleccionado == nuevoScan.tipo) {
        scans.add(nuevoScan);
        // Notificamos a los widgets que escuchan
        notifyListeners();
      }

      return nuevoScan;
    }

    cargarScans() async{
      final scans = await DBProvider.dbProvider.getScans();
      this.scans = [...scans!];
      notifyListeners();
    }

    cargarScansPorTipo(String tipo) async{
      final scans = await DBProvider.dbProvider.getScansByTipo(tipo);
      this.scans = [...scans!];
      tipoSeleccionado = tipo;
      notifyListeners();
    }

    borrarTodosScans() async{
      await DBProvider.dbProvider.deleteAllScan();
      scans = [];
      notifyListeners();
    }

    borrarScanPorId(int id) async{
      await DBProvider.dbProvider.deleteScan(id);
      cargarScansPorTipo(tipoSeleccionado);
    }
}