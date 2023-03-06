import 'package:flutter/material.dart';

class OpeningPage extends StatelessWidget {
  const OpeningPage({super.key});

  @override
  Widget build(BuildContext context) {
    return _openingPage(context);
  }

  Widget _openingPage(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(136, 19, 18, 18),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const Text(
              'Welcome to the To Do Application',
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 30, color: Colors.white),
            ),
            const SizedBox(height: 75),
            Image.asset('assets/images/check-mark-logo.png',
                height: 200, width: 200),
            const SizedBox(height: 125),
            FilledButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(Colors.red),
              ),
              child: const Text(
                'Create Account',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              onPressed: () {
                Navigator.pushNamed(context, '/create_account');
              },
            ),
            const SizedBox(height: 20),
            Row(mainAxisSize: MainAxisSize.min, children: [
              const Text('Already have an account?',
                  style: TextStyle(color: Colors.white)),
              TextButton(
                child: const Text('Sign In',
                    style: TextStyle(
                        decoration: TextDecoration.underline,
                        fontWeight: FontWeight.bold)),
                onPressed: () {
                  Navigator.pushNamed(context, '/sign_in');
                },
              )
            ]),
          ],
        ),
      ),
    );
  }
}
