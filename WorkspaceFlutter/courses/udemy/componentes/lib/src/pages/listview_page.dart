import 'dart:async';
import 'package:flutter/material.dart';

class ListaPage extends StatefulWidget {
  @override
  _ListaPageState createState() => _ListaPageState();
}

class _ListaPageState extends State<ListaPage> {
  /// permite contorllar el scroll
  /// IMPORTANTE: cada vez que se ingresa y sale de está pantalla,
  /// se crean listeners del _scrollController
  ScrollController _scrollController = new ScrollController();

  List<int> _listaNumeros = [];
  int _ultimoItemLista = 0;
  bool _isLoading = false;

  // Método que inicializa y hacer parte del ciclo de vida
  @override
  void initState() {
    super.initState();
    _agregarDiezImagenes();

    _scrollController.addListener(() {
      /**
       * _scrollController.position.maxScrollExtent -> Largo maximo
       */
      if (_scrollController.position.pixels ==
          _scrollController.position.maxScrollExtent) {
        //_agregarDiezImagenes();
        _fetchData();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Listas'),
      ),
      // Stack apila todos juntos
      body: Stack(
        children: [
          _crearLista(),
          _crearLoading(),
        ],
      ),
    );
  }

  Widget _crearLista() {
    return RefreshIndicator(
      // Recibe un Future
      onRefresh: _obtenerPaginaUno, // solo pasamos la referencia del método
      child: ListView.builder(
        controller: _scrollController,
        itemCount: _listaNumeros.length,
        // se encarga de redibujar los elementos dentro de la lista
        itemBuilder: (BuildContext context, int index) {
          final imagen = _listaNumeros[index];

          return FadeInImage(
            image: NetworkImage('https://picsum.photos/500/300/?image=$imagen'),
            placeholder: AssetImage('assets/jar-loading.gif'),
          );
        },
      ),
    );
  }

  Future<Null> _obtenerPaginaUno() async {
    final duration = Duration(seconds: 2);
    new Timer(duration, () {
      _listaNumeros.clear();
      // Queremos nuevas imagenes
      _ultimoItemLista++;
      // cargar 10 registros nuevos
      _agregarDiezImagenes();
    });

    return Future.delayed(duration);
  }

  _agregarDiezImagenes() {
    for (int i = 1; i < 10; i++) {
      _ultimoItemLista++;
      _listaNumeros.add(_ultimoItemLista);
    }

    setState(() {});
  }

  Future<Null> _fetchData() async {
    _isLoading = true;

    setState(() {});

    final duration = new Duration(seconds: 2);
    new Timer(duration, respuestHttp);
  }

  void respuestHttp() {
    _isLoading = false;

    /**
     * Movel el scrol un poco al cargar más imagenes
     */
    _scrollController.animateTo(_scrollController.position.pixels + 100,
        curve: Curves.fastOutSlowIn, duration: Duration(milliseconds: 250));

    _agregarDiezImagenes();
  }

  /// Método que se dispara cuando la página deja de existir
  @override
  void dispose() {
    super.dispose();
    _scrollController.dispose();
  }

  Widget _crearLoading() {
    if (_isLoading) {
      // Cirulo de carga
      return Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [CircularProgressIndicator()],
          ),
          SizedBox(
            height: 15.0,
          )
        ],
      );
    } else {
      return Container();
    }
  }
}
