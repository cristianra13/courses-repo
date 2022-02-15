import 'package:flutter/material.dart';
import 'package:peliculas/providers/movie_provider.dart';
import 'package:peliculas/search/search_delegate.dart';
import 'package:peliculas/widgets/widgets.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    // traer la primera instacia que encuentra del arbol de widgets sobre MoviesProvider
    // Si no encuentra alguna, crea una instancias siempre y cuando se haya definido 
    // ChangeNotifierProvider -> AppState en el main
    // Con listen le decimos que se redibuje cuando haya se llame NotifierListener
    final moviesProvider = Provider.of<MoviesProvider>(context, listen: true);

    return Scaffold (
      appBar: AppBar(
        title: Text('Películas en Cines'),
        elevation: 0,
        actions: [
          IconButton(
            // agregamos el botón de la lupa para buscar
            icon: Icon(Icons.search_outlined),
            onPressed: () => showSearch(
              context: context, 
              delegate: MovieSearchDelegate()
            ),
          )
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            // tarjetas principales
            CardSwiper( movies: moviesProvider.onDisplayMovies ),

            // Slider películas
            MovieSlider(
              movies: moviesProvider.popularMovies,
              title: 'Populares',
              onNextPage: () => moviesProvider.getPopularMovies(),
            )
          ]
        ),
      )
    );
    
  }
  
}