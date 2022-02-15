import 'dart:io';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'package:path_provider/path_provider.dart';
import 'package:rq_reader/models/scan_model.dart';
export 'package:rq_reader/models/scan_model.dart';

class DBProvider {
  static Database? _database;
  static final DBProvider dbProvider = DBProvider._();
  // Siempre obtenemos la misma instancia de la base de datos
  DBProvider._();

  Future<Database?> get database async {
    if (_database != null) {
      return _database;
    }
    _database = await initDB();

    return _database;
  }

  Future<Database> initDB() async {
    // Path de BD
    Directory documentsDirectory = await getApplicationDocumentsDirectory();
    final path = join(documentsDirectory.path, 'ScansDB.db');
    print(path);

    // Crear BD
    return await openDatabase(
      path,
      version: 2,
      onOpen: (db) {},
      onCreate: (Database db, int version) async{
        await db.execute('''
          CREATE TABLE scans (
            id INTEGER PRIMARY KEY,
            tipo TEXT,
            valor TEXT
          )
        ''');
      }
    );
  }

  // debe ser async ya que las interacciones con la db no son sincronas
  Future<int> nuevoScanRaw(ScanModel scanModel) async {

    final id = scanModel.id;
    final tipo = scanModel.tipo;
    final valor = scanModel.valor;

    // Verificar la bd
    final db = await database;
    final res = await db!.rawInsert('''
      INSERT INTO scans (id, tipo, valor) VALUES ($id, $tipo, $valor)
    ''');

    return res;
  }

  Future<int> nuevoScan(ScanModel scanModel) async{
    final db = await database;
    final res = await db!.insert('scans', scanModel.toJson());
    // ignore: avoid_print
    print("$res restart");
    return res;
  }

  Future<ScanModel?> getScanById(int id) async{
    final db = await database;
    // En whereArg se mandan los argumentos del where d emanera posicional
    final res = await db!.query('scans', where: 'id = ?', whereArgs: [id]);

    return res.isNotEmpty 
          ? ScanModel.fromJson(res.first) 
          : null;
  }

  /**
   * Obtenemos todos los scans
   */
  Future<List<ScanModel>?> getScans() async{
    final db = await database;
    // En whereArg se mandan los argumentos del where d emanera posicional
    final res = await db!.query('scans');

    return res.isNotEmpty 
          ? res.map( (s) => ScanModel.fromJson(s) ).toList()
          : [];
  }

  Future<List<ScanModel>?> getScansByTipo(String tipo) async{
    final db = await database;
    // En whereArg se mandan los argumentos del where d emanera posicional
    final res = await db!.rawQuery('''
      SELECT * FROM scans WHERE tipo = '$tipo'
    ''');

    return res.isNotEmpty 
          ? res.map( (s) => ScanModel.fromJson(s) ).toList()
          : [];
  }

  Future<int> updateScan(ScanModel scanModeToUpdate) async{
    final db  = await database;
    // agregamos el where para actualizar solo un registro
    final res = await db!.update("scans", scanModeToUpdate.toJson(), where:  'id = ?', whereArgs: [scanModeToUpdate.id]);
    return res;
  }

  Future<int> deleteScan(int id) async{
    final db = await database;
    final res = await db!.delete('scans', where: 'id = ?', whereArgs: [id]);
    return res;
  }

  Future<int> deleteAllScan() async{
    final db = await database;
    final res = await db!.rawDelete('DELETE FROM scans');
    return res;
  }

}
