

import 'package:flutter/material.dart';
import 'package:rq_reader/providers/db_provider.dart';
import 'package:url_launcher/url_launcher.dart';

launchUr(BuildContext context, ScanModel scan) async {
  final url = scan.valor;

  if (scan.tipo == 'http') {
    if (await canLaunch(url)) {
      await launch(
        url,
        forceSafariVC: false,
        forceWebView: false,
        headers: <String, String>{'my_header_key': 'my_header_value'},
      );
    } else {
      throw 'Could not launch $url';
    }
  } else {
    // Navegamos a mapas
    Navigator.pushNamed(context, 'mapa', arguments: scan);
  }

  
  }