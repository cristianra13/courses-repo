// D7:EB:F6:C9:9A:1B:18:14:EA:D8:46:6B:BF:4E:FB:4C:AC:BE:CC:D2

import 'dart:async';

import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

class PushNotificationService {

  // Obtenemos la instancia de firebaseMessaging
  static FirebaseMessaging messaging = FirebaseMessaging.instance;
  static String? token;
  static StreamController<String> _messageStream = StreamController.broadcast();
  static Stream<String> get messagesStream => _messageStream.stream;

  static Future _backgroundHandler(RemoteMessage message) async {
    // print('background handler ${message.messageId}');
    // Cuando recibimos una notificacion, se añade al stream
    _messageStream.add(message.data['product'] ?? 'No data');
  }

  static Future _onMessageHadler(RemoteMessage message) async {
    //print('onMessage handler ${message.messageId}');
    // Cuando recibimos una notificacion, se añade al stream
    _messageStream.add(message.data['product'] ?? 'No data');
  }

  static Future _onMessageOpenHadler(RemoteMessage message) async {
    // print('onMessage handler ${message.messageId}');
    // Cuando recibimos una notificacion, se añade al stream
    _messageStream.add(message.data['product'] ?? 'No data');
  }


  static Future initializeApp() async {
    // Push notifications
    await Firebase.initializeApp();
    token = await FirebaseMessaging.instance.getToken();
    print(token);

    // handlers
    FirebaseMessaging.onBackgroundMessage(_backgroundHandler);
    FirebaseMessaging.onMessage.listen(_onMessageHadler);
    FirebaseMessaging.onMessageOpenedApp.listen(_onMessageOpenHadler);

    // Local notifications
  }

  static closeStreams() {
    _messageStream.close();
  }


}