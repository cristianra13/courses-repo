// To parse this JSON data, do
//
//     final nowPlayingResponse = nowPlayingResponseFromMap(jsonString);

import 'package:peliculas/models/models.dart';
import 'dart:convert';

class NowPlayingResponse {

    // constructor
    NowPlayingResponse({
        required this.dates,
        required this.page,
        required this.results,
        required this.totalPages,
        required this.totalResults,
    });

    Dates dates;
    int page;
    List<Movie> results;
    int totalPages;
    int totalResults;

    factory NowPlayingResponse.fromJson(String str) => NowPlayingResponse.fromMap(json.decode(str));

    factory NowPlayingResponse.fromMap(Map<String, dynamic> json) => NowPlayingResponse(
      // lee del json y lo mapea
      dates: Dates.fromMap(json["dates"]),
      page: json["page"],
      // Se recibe un listado y se crean instancias de Movie
      results: List<Movie>.from(json["results"].map((x) => Movie.fromMap(x))),
      totalPages: json["total_pages"],
      totalResults: json["total_results"],
    );

}

class Dates {
    Dates({
        required this.maximum,
        required this.minimum,
    });

    DateTime maximum;
    DateTime minimum;

    factory Dates.fromJson(String str) => Dates.fromMap(json.decode(str));

    factory Dates.fromMap(Map<String, dynamic> json) => Dates(
        maximum: DateTime.parse(json["maximum"]),
        minimum: DateTime.parse(json["minimum"]),
    );
}
