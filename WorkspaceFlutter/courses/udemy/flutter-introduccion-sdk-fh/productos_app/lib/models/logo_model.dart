import 'dart:convert';

class LogoModel {
    LogoModel({
        required this.respuesta,
        required this.urlLogo,
    });

    String respuesta;
    String urlLogo;

    factory LogoModel.fromJson(String str) => LogoModel.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory LogoModel.fromMap(Map<String, dynamic> json) => LogoModel(
        respuesta: json["respuesta"],
        urlLogo: json["url_logo"],
    );

    Map<String, dynamic> toMap() => {
        "respuesta": respuesta,
        "url_logo": urlLogo,
    };
}
