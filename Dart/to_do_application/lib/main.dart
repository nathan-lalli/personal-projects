import 'package:flutter/material.dart';
import 'pages/home_page.dart';
import 'pages/new_task_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      initialRoute: "/home",
      routes: {
        "/home": (context) => const HomePage(),
        "/new_task": (context) => const NewTaskPage()
      },
    );
  }
}
