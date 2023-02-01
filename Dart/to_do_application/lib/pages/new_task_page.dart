import 'package:flutter/material.dart';
import 'package:to_do_application/pages/home_page.dart';

class NewTaskPage extends StatelessWidget {
  const NewTaskPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Add New Task'),
          backgroundColor: const Color.fromARGB(255, 141, 140, 140),
        ),
        backgroundColor: const Color.fromARGB(136, 19, 18, 18),
        body: ListView(children: [
          const SizedBox(height: 20),
          const Text(
            'Task Description:',
            style: TextStyle(color: Colors.white),
          ),
          const SizedBox(height: 20),
          const TextField(
            cursorColor: Colors.white,
            style: TextStyle(color: Colors.white),
            decoration: InputDecoration(
              prefix: Icon(Icons.description, color: Colors.white),
              focusedBorder: OutlineInputBorder(
                borderSide: BorderSide(color: Colors.white),
              ),
              enabledBorder: OutlineInputBorder(
                borderSide: BorderSide(color: Colors.white),
              ),
            ),
          ),
          const SizedBox(height: 20),
          ElevatedButton(
              style: const ButtonStyle(
                  backgroundColor: MaterialStatePropertyAll<Color>(
                      Color.fromARGB(255, 97, 97, 97))),
              onPressed: () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (_) => const HomePage()));
              },
              child: const Text('Save'))
        ]));
  }
}
