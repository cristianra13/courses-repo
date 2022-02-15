import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:rq_reader/providers/db_provider.dart';


class MapaScreen extends StatefulWidget {

  @override
  State<MapaScreen> createState() => _MapaScreenState();
}

class _MapaScreenState extends State<MapaScreen> {

  Completer<GoogleMapController> _controller = Completer();
  MapType mapType = MapType.normal;

  @override
  Widget build(BuildContext context) {

    final ScanModel scan = ModalRoute.of(context)!.settings.arguments as ScanModel;
    final CameraPosition puntoInicial = CameraPosition(
      target: scan.getLatLng(),
      zoom: 17.5,
      tilt: 50
    );
    Set<Marker> markers = Set<Marker>();
    markers.add(Marker(
      markerId: MarkerId('geo-location'),
      position: scan.getLatLng()
    ));

    return Scaffold(
      appBar: AppBar(
        title: Text('Mapa'),
        actions: [
          IconButton(
            icon: Icon(Icons.location_disabled),
            onPressed: () async{
              final GoogleMapController controller = await _controller.future;
              controller.animateCamera(
                CameraUpdate.newCameraPosition(
                  CameraPosition(
                    target: scan.getLatLng(),
                    zoom: 17.5,
                    tilt: 50
                  )
                )
              );
            },
          )
        ],
      ),
      body: GoogleMap(
        myLocationButtonEnabled: false,
        mapType: mapType,
        markers: markers,
        initialCameraPosition: puntoInicial,
        onMapCreated: (GoogleMapController controller) {
          _controller.complete(controller);
        }
      ),
      floatingActionButton: FloatingActionButton (
        child: Icon(Icons.layers),
        onPressed: () {
          if (mapType == MapType.normal) {
            mapType = MapType.satellite;
          } else {
            mapType = MapType.normal;
          }
          // Redibujamos el stateFullWidget
          setState(() {});
        },
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.startFloat

   );
  }
}