import 'package:encrypt/encrypt.dart';

class EncryptData{
//for AES Algorithms

  static Encrypted? encrypted;
  static var decrypted;
  static const String _key = "dGhpc19pc19rZXlfdG9fZmx1dHRlcg==";


 static String encryptAES(String plainText){
  final key = Key.fromUtf8(_key);
  final iv = IV.fromLength(16);
  final encrypter = Encrypter(AES(key));
  encrypted = encrypter.encrypt(plainText, iv: iv);
  print(encrypted!.base64);
  return encrypted!.base64;
 }

  static String decryptAES(String plainText){
    final key = Key.fromUtf8(_key);
    final iv = IV.fromLength(16);
    final encrypter = Encrypter(AES(key));
    decrypted = encrypter.decrypt(encrypted!, iv: iv);
    print(decrypted);
    return decrypted;
  }
}