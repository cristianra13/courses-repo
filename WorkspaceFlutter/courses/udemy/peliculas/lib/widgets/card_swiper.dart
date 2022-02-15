import 'package:card_swiper/card_swiper.dart';
import 'package:flutter/material.dart';
import 'package:peliculas/models/models.dart';

class CardSwiper extends StatelessWidget {

  final List<Movie> movies;

  const CardSwiper({Key? key, required this.movies}) : super(key: key);



  @override
  Widget build(BuildContext context) {
    // MediaQuery nos permite saber las especificaciones del dispositivo donde está corriendo la app
    final size = MediaQuery.of(context).size;

    // Antes de construir el Swiper
    if (this.movies.length == 0) {
      return Container(
        width: double.infinity,
        height: size.height * 0.5,
        child: Center(
          child: CircularProgressIndicator()
        )
      );
    }

    return Container(
      // Toma todo el ancho posible basado en el padre
      width: double.infinity,
      // Obtenemos la mitad de la pantalla
      height: size.height * 0.5,
      // color: Colors.red,
      child: Swiper(
        // Cantidad de tarjetas a manejar
        itemCount: movies.length,
        layout: SwiperLayout.STACK,
        itemWidth: size.width * 0.6,
        itemHeight: size.height * 0.45,
        itemBuilder: (_, int index) {

          final movie = movies[index];
          movie.heroId = 'swiper-${movie.id}';

          // retornamos un widget que va a ser utilizado para renderizar la tarjeta
          return GestureDetector(
            onTap: () => Navigator.pushNamed(context, 'details', arguments: movie),
            child: Hero(
              tag: movie.heroId!, // EL TAG DEBE SER UNICO
              child: ClipRRect( // Imagen redondeada
                borderRadius: BorderRadius.circular(20),
                child: FadeInImage(
                  placeholder: AssetImage('assets/no-image.jpg'),
                  image: NetworkImage(movie.fullPosterImg),
                  // Adpatamos la imagen al tamaño del contenedor padre
                  fit: BoxFit.cover,
                ),
              ),
            ),
          );
        },
      ),
    );
  }
}
