// To parse this JSON data, do
//
//     final categoriesModel = categoriesModelFromMap(jsonString);

import 'dart:convert';

class CategoriesModel {
    CategoriesModel({
        required this.respuesta,
        required this.datos,
    });

    String respuesta;
    List<Dato> datos;

    factory CategoriesModel.fromJson(String str) => CategoriesModel.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory CategoriesModel.fromMap(Map<String, dynamic> json) => CategoriesModel(
        respuesta: json["respuesta"],
        datos: List<Dato>.from(json["datos"].map((x) => Dato.fromMap(x))),
    );

    Map<String, dynamic> toMap() => {
        "respuesta": respuesta,
        "datos": List<dynamic>.from(datos.map((x) => x.toMap())),
    };
}

class Dato {
    Dato({
        required this.id,
        required this.nombre,
        required this.descripcion,
        required this.urlImagen,
    });

    int id;
    String nombre;
    String descripcion;
    String urlImagen;

    factory Dato.fromJson(String str) => Dato.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Dato.fromMap(Map<String, dynamic> json) => Dato(
        id: json["id"],
        nombre: json["nombre"],
        descripcion: json["descripcion"],
        urlImagen: json["url_imagen"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "nombre": nombre,
        "descripcion": descripcion,
        "url_imagen": urlImagen,
    };
}
