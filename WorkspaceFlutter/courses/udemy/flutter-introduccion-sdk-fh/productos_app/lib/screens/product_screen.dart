import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:image_picker/image_picker.dart';
import 'package:productos_app/providers/product_form_provider.dart';
import 'package:productos_app/services/services.dart';
import 'package:productos_app/ui/input_decorations.dart';
import 'package:productos_app/widgets/widgets.dart';
import 'package:provider/provider.dart';


class ProductScreen extends StatelessWidget {

  static const String id = 'product_screen';

  @override
  Widget build(BuildContext context) {

    final productService = Provider.of<ProductService>(context);
    return ChangeNotifierProvider(
      create: (_) => ProductFormProvider(productService.selectedProduct),
      child: _ProductsScreenBody(productService: productService),
    );

    // TODO: pendiente si toca cambiar Scaffold por SafeArea
    // return _ProductsScreenBody(productService: productService);
  }
}

class _ProductsScreenBody extends StatelessWidget {
  const _ProductsScreenBody({
    Key? key,
    required this.productService,
  }) : super(key: key);

  final ProductService productService;

  @override
  Widget build(BuildContext context) {
    final productForm = Provider.of<ProductFormProvider>(context);

    return Scaffold(
      body: SingleChildScrollView(
        // Cuando se haga scroll se oculta el teclado
        // keyboardDismissBehavior: ScrollViewKeyboardDismissBehavior.onDrag,
        child: Column(
          children: [
            
            Stack(
              children: [
                ProductImage(url: productService.selectedProduct.picture),

                Positioned(
                  top: 60,
                  left: 20,
                  child: IconButton(
                    onPressed: () => Navigator.of(context).pop(),
                    icon: Icon(Icons.arrow_back_ios_new, size: 40, color: Colors.white),
                  )
                ),

                // Positioned(
                //   top: 60,
                //   right: 30,
                //   child: IconButton(
                //     onPressed: () async{
                //       final picker = ImagePicker();
                //       final XFile? pickedFile = await picker.pickImage(
                //         source: ImageSource.camera,
                //         imageQuality: 100
                //       );

                //       if (pickedFile == null)  {
                //         print('No selecciono nada ${pickedFile?.path}');
                //         return;
                //       }
                //       print('Tenemos imagen');
                //       productService.updateSelectedProductImage(pickedFile.path);
                //     },
                    
                //     icon: Icon(Icons.camera_alt_outlined, size: 40, color: Colors.white),
                //   )
                // )

              ],
            ),

            _ProductForm(),

            SizedBox(height: 100)

          ],
        ),
      ),

      floatingActionButtonLocation: FloatingActionButtonLocation.endDocked,
      floatingActionButton: FloatingActionButton(
        backgroundColor: Color.fromRGBO(255, 61, 0, 1),
        child: productService.isSaving
        ? CircularProgressIndicator(color: Color.fromRGBO(255, 61, 0, 1),)
        : Icon(Icons.add_shopping_cart_outlined),
        onPressed: () {
          productService.productsInCart.add(productService.selectedProduct);
          productService.productsInCart.forEach((element) => print(element));
        },
      ),
   );
  }
}

class _ProductForm extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    final productForm = Provider.of<ProductFormProvider>(context);
    final product = productForm.product;

    return Padding(
      padding: EdgeInsets.symmetric(horizontal: 10),
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 20),
        width: double.infinity,
        // debe estár el height determinado por los hijos
        decoration: _buildBoxDecoration(),
        child: Form(
          key: productForm.formKey,
          autovalidateMode: AutovalidateMode.onUserInteraction,
          child: Column(
            children: [
              SizedBox(height: 10),

              TextFormField(
                enabled: false,
                initialValue: product.name,
                onChanged: (value) => product.name = value,
                validator: (value) {
                  if (value == null || value.length < 1)
                  return 'Nombre es obligatorio';
                },
                decoration: InputDecorations.authinputDecoration(
                  hintText: 'Nombre del producto',
                  labelText: 'Nombre'
                ),
              ),

              SizedBox(height: 30,),

              TextFormField(
                enabled: false,
                initialValue: '${product.price}',
                inputFormatters: [
                  FilteringTextInputFormatter.allow(RegExp(r'^(\d+)?\.?\d{0,2}'))
                ],
                onChanged: (value) {
                  if (double.tryParse(value) == null) {
                    product.price = 0;
                  } else {
                    product.price = double.parse(value);
                  }
                },
                // Tipo de teclado a mostrar
                keyboardType: TextInputType.number,
                decoration: InputDecorations.authinputDecoration(
                  hintText: '\$150',
                  labelText: 'Precio'
                ),
              ),

              SizedBox(height: 30),

              TextFormField(
                enabled: false,
                initialValue: 'Disponible'
              ),

              // SwitchListTile(
              //   value: product.available,
              //   title: Text('Disponible'),
              //   activeColor: Colors.indigo,
              //   onChanged: productForm.updateAvailability
              // ),

              SizedBox(height: 30),
            ],
          )
        ),
      ),
    );
  }

  BoxDecoration _buildBoxDecoration() {

    return BoxDecoration(
      color: Colors.white,
      borderRadius: BorderRadius.only(bottomLeft: Radius.circular(25), bottomRight: Radius.circular(25)),
      boxShadow: [
        BoxShadow(
          color: Colors.black.withOpacity(0.05),
          offset: Offset(0, 5),
          blurRadius: 5
        )
      ]
    );
  }
}