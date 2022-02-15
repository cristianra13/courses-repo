import 'package:flutter/material.dart';
import 'package:peliculas/models/models.dart';
import 'package:peliculas/widgets/widgets.dart';

class DetailsScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    // Casteamos a Movie
    final Movie movie = ModalRoute.of(context)!.settings.arguments as Movie;

    return Scaffold(
        body: CustomScrollView(
      /**
         * Los slivers son widgets que tienen cierto comportamiento preprogramado 
         * cuando se hace scroll en el contenido del padre
         */
      slivers: [
        _CustomAppBar(movie),
        SliverList(
          delegate: SliverChildListDelegate([
            _PosterAndTitle(movie),
            _Overview(movie),
            CastingCards( movie.id )
          ]),
        )
      ],
    ));
  }
}

class _CustomAppBar extends StatelessWidget {
  
  final Movie movie;

  const _CustomAppBar(this.movie);
  
  @override
  Widget build(BuildContext context) {
    
    return SliverAppBar(
      backgroundColor: Colors.indigo,
      expandedHeight: 200,
      floating: false,
      pinned: true, // nunca desaparezca
      flexibleSpace: FlexibleSpaceBar(
        centerTitle: true,
        titlePadding: EdgeInsets.all(0),
        title: Container(
          width: double.infinity,
          padding: EdgeInsets.only(bottom: 10, left: 10, right: 10),
          alignment: Alignment.bottomCenter,
          color: Colors.black12,
          child: Text(
            movie.title,
            style: TextStyle(
              fontSize: 16,
            ),
          ),

        ),
        background: FadeInImage(
          placeholder: AssetImage('assets/loading.gif'),
          image: NetworkImage(movie.fullBackdropPath),
          // para expandirla sin perder las dimensiones de la imagen
          fit: BoxFit.cover,
        ),
      ),
    );
  }

}

class _PosterAndTitle extends StatelessWidget {

  final Movie movie;

  const _PosterAndTitle(this.movie);

  @override
  Widget build(BuildContext context) {

    final TextTheme textTheme = Theme.of(context).textTheme;
    // Obtenemos el tamaño de la pantalla
    final size = MediaQuery.of(context).size;

    return Container(
      margin: EdgeInsets.only(top: 20),  
      padding: EdgeInsets.symmetric(horizontal: 20),
      child: Row(
        children: [
          Hero(
            tag: movie.heroId!,
            child: ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: FadeInImage(
                placeholder: AssetImage('assets/no-image.jpg'),
                image: NetworkImage(movie.fullPosterImg),
                height: 150,
              ),
            ),
          ),
          
          SizedBox(width: 20),

          ConstrainedBox(
            constraints: BoxConstraints(maxWidth: size.width - 200),
            child: Column(
              // Alinear al inicio
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  movie.title,
                  style: textTheme.headline5,
                  overflow: TextOverflow.ellipsis,
                  // si se pasa de dos lienas el titulo, agrega los puntos ...
                  maxLines: 3,
                ),
                
          
                Text(
                  movie.originalTitle,
                  style: textTheme.subtitle1,
                  overflow: TextOverflow.ellipsis,
                  // si se pasa de dos lienas el titulo, agrega los puntos ...
                  maxLines: 3,
                ),
          
                Row(
                  children: [
                    Icon(Icons.star_outline, size: 15, color: Colors.grey,),
                    SizedBox(width: 5),
                    Text(movie.voteAverage.toString(), style: textTheme.caption,)
                  ],
                )
              ],
            ),
          )
        ],
      ),
    );

  }


}

class _Overview extends StatelessWidget {

  final Movie movie;

  const _Overview(this.movie);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text(
        movie.overview,
        textAlign: TextAlign.justify,
        style: Theme.of(context).textTheme.subtitle1,
      ),  
      padding: EdgeInsets.symmetric(horizontal: 30, vertical: 10)
    );

  }
}