import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rq_reader/providers/scan_list_provider.dart';
import 'package:rq_reader/utils/utils.dart';

class ScanTiles extends StatelessWidget {

  final String tipo;

  const ScanTiles({required this.tipo}) ;

  @override
  Widget build(BuildContext context) {
    
    // listen: true para que cada vez que haya un nuevo scan se redibuje la pantalla
    final scanListProvider = Provider.of<ScanListProvider>(context);
    final scans = scanListProvider.scans;

    return ListView.builder(
      itemCount: scans.length,
      itemBuilder: (_, i) => Dismissible(
        key: UniqueKey(),
        background: Container(
          color: Colors.red,
        ),
        onDismissed: (DismissDirection direction) {
          // eliminamos de la bd
          Provider.of<ScanListProvider>(context, listen: false).borrarScanPorId(scans[i].id!);
        },
        child: ListTile(
          leading: Icon(
            tipo == 'http' ? Icons.home_outlined : Icons.map_outlined, 
            color: Theme.of(context).primaryColor),
          title: Text(scans[i].valor),
          subtitle: Text(scans[i].id.toString()),
          // Indicamos que puede hacer tap en cada una de las opciones
          trailing: const Icon(Icons.keyboard_arrow_right, color: Colors.grey),
          onTap: () => launchUr(context, scans[i]) ,
        ),
      )
    );

  }
}