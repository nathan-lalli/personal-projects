import 'package:flutter/material.dart';
import '../utils/account_validator.dart';
import '../controllers/auth_controller.dart';

class SignInPage extends StatefulWidget {
  const SignInPage({super.key});

  @override
  State<SignInPage> createState() => _SignInPageState();
}

class _SignInPageState extends State<SignInPage> {
  final _formKey = GlobalKey<FormState>();

  final _emailController = TextEditingController();
  final _pwController = TextEditingController();
  String? _errorMessage = '';
  final AuthController _authController = AuthController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: const Text('Sign In', style: TextStyle(color: Colors.white)),
          backgroundColor: Colors.red),
      backgroundColor: const Color.fromARGB(136, 19, 18, 18),
      body: Form(
        key: _formKey,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Row(children: [
              const Text('Email: ', style: TextStyle(color: Colors.white)),
              const SizedBox(width: 37),
              SizedBox(
                width: 330,
                child: TextFormField(
                    keyboardType: TextInputType.emailAddress,
                    controller: _emailController,
                    cursorColor: Colors.white,
                    style: const TextStyle(color: Colors.white),
                    decoration: const InputDecoration(
                        labelText: 'Email Address',
                        labelStyle: TextStyle(color: Colors.white),
                        prefix: Icon(Icons.description, color: Colors.white),
                        focusedBorder: OutlineInputBorder(
                          borderSide: BorderSide(color: Colors.white),
                        ),
                        enabledBorder: OutlineInputBorder(
                          borderSide: BorderSide(color: Colors.white),
                        ))),
              )
            ]),
            const SizedBox(height: 10),
            Row(children: [
              const Text('Password: ', style: TextStyle(color: Colors.white)),
              const SizedBox(width: 10),
              SizedBox(
                  width: 330,
                  child: TextFormField(
                      obscureText: true,
                      enableSuggestions: false,
                      autocorrect: false,
                      controller: _pwController,
                      cursorColor: Colors.white,
                      style: const TextStyle(color: Colors.white),
                      decoration: const InputDecoration(
                          labelText: 'Password',
                          labelStyle: TextStyle(color: Colors.white),
                          prefix: Icon(Icons.description, color: Colors.white),
                          focusedBorder: OutlineInputBorder(
                            borderSide: BorderSide(color: Colors.white),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderSide: BorderSide(color: Colors.white),
                          ))))
            ]),
            const SizedBox(height: 125),
            FilledButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(Colors.red),
              ),
              child: const Text(
                'Sign In',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              onPressed: () {
                // validate the form
                if (validateEmailAddress(_emailController.text) != null) {
                  // show the error message on the screen using _errorMessage
                  _errorMessage = validateEmailAddress(_emailController.text);
                } else if (validatePassword(_pwController.text) != null) {
                  // show the error message on the screen using _errorMessage
                  _errorMessage = validatePassword(_pwController.text);
                } else if (_formKey.currentState!.validate()) {
                  // create the account
                  _authController
                      .signIn(
                          email: _emailController.text,
                          password: _pwController.text)
                      .then((value) {
                    if (value == null) {
                      Navigator.pushNamed(context, '/home');
                    } else {
                      // show the error message on the screen using _errorMessage
                      _errorMessage = value;
                    }
                  });
                }
              },
            ),
            Text(_errorMessage!, style: const TextStyle(color: Colors.white)),
            const SizedBox(height: 50),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _emailController.dispose();
    _pwController.dispose();
    super.dispose();
  }
}
