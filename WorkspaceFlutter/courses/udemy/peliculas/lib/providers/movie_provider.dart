
import 'dart:async';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:peliculas/helpers/debouncer.dart';
import 'package:peliculas/models/models.dart';

// Los provider deben heredar siempre de ChangeNotifier
class MoviesProvider extends ChangeNotifier {

  String _baseUrl = 'api.themoviedb.org';
  String _apiKey = '1aa0eda58930940bb59170176807b901';
  String _language = 'es-ES';

  List<Movie> onDisplayMovies = [];
  List<Movie> popularMovies = [];

  Map<int, List<Cast>> moviesCast = {};

  int _popularPage = 0;


  final debouncer  = Debouncer(
    duration: Duration(milliseconds: 500)
  );

  final StreamController<List<Movie>> _streamController = new StreamController.broadcast();
  Stream<List<Movie>> get suggestionStream => this._streamController.stream;

  MoviesProvider() {
    this.getOnDisplayMovies();
    this.getPopularMovies();
  }

  // Consumo de servicio de películas, page es opcional
  Future<String> _getJsonData(String endpoint, {int page = 1}) async {
    final url =
      Uri.https(_baseUrl, endpoint, {
        'api_key': _apiKey,
        'language': _language,
        'page': '$page'
      });

    // Await the http get response, then decode the json-formatted response.
    var response = await http.get(url);
    return response.body;
  }

  // LLamado de HTTP
  getOnDisplayMovies() async {
    
    final jsondata = await this._getJsonData('/3/movie/now_playing');
    final nowPlayingResponse = NowPlayingResponse.fromJson(jsondata);

    this.onDisplayMovies = nowPlayingResponse.results;
    // le dice a todos los widgets que esten escuchando ese cambio, para que lo redibujen
    notifyListeners();
  }

  getPopularMovies() async {
    _popularPage++; // aumento de página
    final jsondata = await this._getJsonData('/3/movie/popular', page: _popularPage);
    final popularResponse = PopularResponse.fromJson(jsondata);

    // se concatenan los dos resultados
    this.popularMovies = [ ...popularMovies, ...popularResponse.results ];
    // le dice a todos los widgets que esten escuchando ese cambio, para que lo redibujen
    notifyListeners();
  }

  // asyn convierte cualquier retorno en un Future
  Future<List<Cast>> getMoviesCast(int movieId) async {

    if (moviesCast.containsKey(movieId)) 
      return moviesCast[movieId]!;

    print('Pidiendo infor al server-cast');

    // Hacemos el consumo
    final jsondata = await this._getJsonData('/3/movie/$movieId/credits');
    final creditsResponse = CreditsResponse.fromJson(jsondata);

    moviesCast[movieId] = creditsResponse.cast;

    return creditsResponse.cast;
  }

  Future<List<Movie>> searchMovie(query) async {
    var url =
      Uri.https(_baseUrl, '3/search/movie', {
        'api_key': _apiKey,
        'language': _language,
        'query': query
      });


    final response = await http.get(url);
    final searchResponse =  SearchResponse.fromJson(response.body);

    return searchResponse.results;
  }

  void getSuggestionByQuery(String query) {
    debouncer.value = '';
    // Método que se llama cuando pasan las 500 ml de segundo
    debouncer.onValue = (value) async {

      final results = await this.searchMovie(query);
      this._streamController.add(results);

    };

    final timer = Timer.periodic(Duration(milliseconds: 300), (_) {
      debouncer.value = query;
    });

    Future.delayed(Duration(milliseconds: 301)).then((_) => timer.cancel());
  }
}