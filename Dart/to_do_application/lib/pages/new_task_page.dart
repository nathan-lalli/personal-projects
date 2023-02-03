import 'package:flutter/material.dart';
import 'package:to_do_application/pages/home_page.dart';
import '../controllers/task_controller.dart';

class NewTaskPage extends StatelessWidget {
  const NewTaskPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Create a text controller to get the values in the text field
    final TextEditingController textController = TextEditingController();

    return Scaffold(
        appBar: AppBar(
          title:
              const Text('Add New Task', style: TextStyle(color: Colors.white)),
          backgroundColor: Colors.red,
        ),
        backgroundColor: const Color.fromARGB(136, 19, 18, 18),
        body: ListView(children: [
          const SizedBox(height: 20),
          const Text(
            'Task Description:',
            style: TextStyle(color: Colors.white),
          ),
          const SizedBox(height: 20),
          TextField(
            controller: textController,
            cursorColor: Colors.white,
            style: const TextStyle(color: Colors.white),
            decoration: const InputDecoration(
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
                // Insert the new task into the database
                TaskController().insertTask(textController.text);
                // Navigate back to the home page
                Navigator.of(context).pushReplacementNamed("/home");
              },
              child: const Text('Save'))
        ]));
  }
}
