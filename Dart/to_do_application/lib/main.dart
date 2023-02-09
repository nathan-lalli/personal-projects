import 'package:flutter/material.dart';
import 'pages/home_page.dart';
import 'pages/new_task_page.dart';
import 'pages/opening_page.dart';
import 'pages/create_account_page.dart';
import 'pages/sign_in_page.dart';

//Firebase imports
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    name: "todoapplication-f2df7",
    options: DefaultFirebaseOptions.currentPlatform,
  );
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
        "/open": (context) => const OpeningPage(),
        "/home": (context) => const HomePage(),
        "/new_task": (context) => const NewTaskPage(),
        "/create_account": (context) => const CreateAccountPage(),
        "/sign_in": (context) => const SignInPage(),
      },
    );
  }
}
