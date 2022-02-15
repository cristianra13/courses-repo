import 'dart:convert';

class UserRegisterModel {
    UserRegisterModel({
        required this.name,
        required this.city,
        required this.email,
        required this.password
    });

    String name;
    String city;
    String email;
    String password;

    factory UserRegisterModel.fromJson(String str) => UserRegisterModel.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory UserRegisterModel.fromMap(Map<String, dynamic> json) => UserRegisterModel(
        name: json["name"],
        city: json["city"],
        email: json["email"],
        password: json["password"],
    );

    Map<String, dynamic> toMap() => {
        "name": name,
        "city": city,
        "email": email,
        "password": password,
    };
}
