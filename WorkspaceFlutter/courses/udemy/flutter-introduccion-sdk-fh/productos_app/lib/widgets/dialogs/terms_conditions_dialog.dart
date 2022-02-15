import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_markdown/flutter_markdown.dart';

class PolicyDialog extends StatelessWidget {

  final double radius;
  final String policyFilename;

  const PolicyDialog({
    Key? key, 
    this.radius = 8, 
    required this.policyFilename
  }) : super(key: key); // assert(policyFilename.contains('.md'))

  @override
  Widget build(BuildContext context) {

    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(radius)
      ),
      child: Column(
        children: [
          Expanded(
            child: FutureBuilder(
              future: DefaultAssetBundle.of(context)
                .loadString('assets/terms_policy/$policyFilename'),
              // Future.delayed(Duration(milliseconds: 150)).then((value) {
              //   return rootBundle.loadString('assets/terms_policy.md');
              // }),
              builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
                if (snapshot.hasData) {
                  return Markdown(
                    data: snapshot.data!,

                  );
                }
                return Center(
                  child: CircularProgressIndicator(),
                );
              },
            ),
          ),
          
          TextButton(
            // add padding with EdgeInsets.all(0)
            child: Container(
              padding: EdgeInsets.all(0),
              color: Colors.blue,
              child: Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.only(
                    bottomLeft: Radius.circular(radius),
                    bottomRight: Radius.circular(radius)
                  ),
                ),
                alignment: Alignment.center,
                height: 50,
                width: double.infinity,
                child: Text(
                  'Cerrar',
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    color: Theme.of(context).textTheme.button!.color
                  ),
                ),
              ),

            ),
            onPressed: () {
              
            },
          ),
          TextButton(
            // add padding with EdgeInsets.all(0)
            child: Container(
              padding: EdgeInsets.all(0),
              color: Colors.blue,
              child: Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.only(
                    bottomLeft: Radius.circular(radius),
                    bottomRight: Radius.circular(radius)
                  ),
                ),
                alignment: Alignment.center,
                height: 50,
                width: double.infinity,
                child: Text(
                  'Aceptar',
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    color: Theme.of(context).textTheme.button!.color
                  ),
                ),
              ),

            ),
            onPressed: () => Navigator.of(context).pop(),
          ),
        ],
      ),
    );
  }
}