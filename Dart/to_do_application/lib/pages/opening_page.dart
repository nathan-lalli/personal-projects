import 'package:flutter/material.dart';

class OpeningPage extends StatelessWidget {
  const OpeningPage({super.key});

  @override
  Widget build(BuildContext context) {
    return _openingPage(context);
  }

  Widget _openingPage(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const Text(
              'Welcome to the To Do Application',
              style: TextStyle(fontSize: 30),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              child: const Text('Create Account'),
              onPressed: () {
                Navigator.pushNamed(context, '/create_account');
              },
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              child: const Text('Sign In'),
              onPressed: () {
                Navigator.pushNamed(context, '/sign_in');
              },
            ),
          ],
        ),
      ),
    );
  }
}
