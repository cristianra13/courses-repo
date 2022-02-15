import 'package:flutter/material.dart';
import 'package:productos_app/providers/login_form_provider.dart';
import 'package:productos_app/screens/main_menu.dart';
import 'package:productos_app/screens/screens.dart';
import 'package:productos_app/services/services.dart';
import 'package:provider/provider.dart';
import 'package:productos_app/ui/input_decorations.dart';
import 'package:productos_app/widgets/widgets.dart';


class LoginScreen extends StatelessWidget {

  static const String id = 'login_screen';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: AuthBackground(
        child: SingleChildScrollView(

          child: Column(
            children: [
              SizedBox(
                height: 200,
              ),

              CardContainer(
                child: Column(
                  children: [
                    SizedBox(height: 10,),
                    Text('Login', style: Theme.of(context).textTheme.headline4,),
                    SizedBox(height: 30,),
                    
                    ChangeNotifierProvider(
                      create: (_) => LoginFormProvider() ,
                      child: _LoginForm(),
                    )
                  ],
                ),
              ),
              SizedBox(height: 50,),
              TextButton(
                onPressed: () {
                  Navigator.pushReplacementNamed(context, RegisterScreen.id);
                },
                style: ButtonStyle(
                  overlayColor: MaterialStateProperty.all(Colors.indigo.withOpacity(0.1)),
                  shape: MaterialStateProperty.all(StadiumBorder())
                ),
                child: Text(
                  'Crear una nueva cuenta', 
                  style: TextStyle(
                    fontSize: 18, 
                    color: Colors.black87
                  ),
                ),
              ),
              SizedBox(height: 50,),

            ],
          ),
        )
      )
    );
  }

}

class _LoginForm extends StatelessWidget {


  @override
  Widget build(BuildContext context) {

    final loginForm  = Provider.of<LoginFormProvider>(context);
    Color color = Color.fromRGBO(255, 57, 17, 1);

    return Container(
      child: Form(
        // Conectamos al objeto de login_form_provider -> GlobalKey<FormState> formKey
        key: loginForm.formKey,
        autovalidateMode: AutovalidateMode.onUserInteraction,

        child: Column(
          children: [
            TextFormField(
              autocorrect: false, // para que no intenete autocorregir texto del correo
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecorations.authinputDecoration(
                hintText: 'john.doe@email.com',
                labelText: 'Correo electrónico',
                prefixIcon: Icons.alternate_email_sharp
              ),
              onChanged: (value) {
                loginForm.email = value;
              },
              validator: (value) {
                return _validateEmailFormat(value);
              },
            ),

            SizedBox(height: 30,),

            TextFormField(
              autocorrect: false, // para que no intenete autocorregir texto del correo
              obscureText: true, // Ocultamos lo que se escribe
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecorations.authinputDecoration(
                hintText: '******',
                labelText: 'Contraseña',
                prefixIcon: Icons.lock_outline
              ),
              onChanged: (value) {
                loginForm.password = value;
              },
              validator: (value) {
                return _validateFormatpassword(value);
              },
            ),

            SizedBox(height: 30,),

            MaterialButton(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(10),
              ),
              disabledColor: Colors.grey,
              elevation: 0,
              color: color,
              child: Container(
                padding: EdgeInsets.symmetric(horizontal: 80, vertical: 15),
                child: Text(
                  loginForm.isLoading
                  ? 'Cargando...'
                  : 'ingresar',
                  style: TextStyle(color: Colors.white),
                )
              ),
              // Deshabilitamos el botón si está cargando
              onPressed: loginForm.isLoading ? null : ()  async{
                // Ocultamos el teclado al oprimir el botń
                FocusScope.of(context).unfocus();
                final authService = Provider.of<AuthService>(context, listen: false);

                if (!loginForm.isValidForm()) return;

                loginForm.isLoading = true;

                final String? message = 
                  await authService.login(loginForm.email, loginForm.password);

                if (message == null) {
                  // pushReplacementNamed destruye el stack de las pantallas y nos deja en home
                  Navigator.pushReplacementNamed(context, MainMenuScreen.id);
                } else {
                  // TODO: Mostrar error en pantalla
                  print(message);
                  NotificationsService.showSnackbar(message);
                  loginForm.isLoading = false;
                }
              }
            )
          ],
        ),
      )
    );
  }

  String? _validateEmailFormat(String? value) {
    String pattern = r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
    RegExp regExp  = new RegExp(pattern);
    return  regExp.hasMatch(value ?? '')
      ? null
      : 'El formato del correo no es valido';
  }

  String? _validateFormatpassword(String? value) {
    return (value != null && value.length >= 6) 
    ? null 
    : 'La contraseña debe tener minimo 6 caracteres';
  }

}