import 'package:flutter/material.dart';

class SecondaryPage extends StatelessWidget {
  const SecondaryPage({Key? key, required this.value}) : super(key: key);

  final int value;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text('$value'),
      ),
    );
  }
}
