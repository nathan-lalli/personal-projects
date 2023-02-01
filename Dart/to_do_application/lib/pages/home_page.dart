// I think this will take at least 2 hours to complete
// It actually took me about 3 hours or so to complete
// I had to look up a lot of things in order to find the best way to complete
// the different things I wanted to do. I also had a lot of issues with the syntax of flutter again

import 'package:flutter/material.dart';
import 'package:to_do_application/pages/new_task_page.dart';

import '../controllers/task_controller.dart';
import '../model/task.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final Future<List<Task>> _tasksFuture = TaskController().getTasks();
  List<Task>? _tasks;

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<List<Task>>(
        future: _tasksFuture,
        builder: (context, snapshot) {
          if (_tasks == null && snapshot.hasData) {
            _tasks = snapshot.data;
          }
          if (_tasks == null) {
            return Scaffold(
              appBar: AppBar(title: const Text('Todo')),
              body: const CircularProgressIndicator(),
            );
          }
          return Scaffold(
              appBar: AppBar(
                title:
                    const Text('Todo', style: TextStyle(color: Colors.white)),
                backgroundColor: Colors.red,
              ),
              backgroundColor: const Color.fromARGB(136, 19, 18, 18),
              body: ListView.separated(
                padding: const EdgeInsets.all(5),
                itemBuilder: (_, index) => _toWidget(index),
                separatorBuilder: (_, __) => const Divider(),
                itemCount: _tasks!.length,
              ),
              floatingActionButton:
                  Column(mainAxisAlignment: MainAxisAlignment.end, children: [
                FloatingActionButton(
                  onPressed: () {
                    Navigator.of(context).push(
                        MaterialPageRoute(builder: (_) => const NewTaskPage()));
                  },
                  backgroundColor: Colors.red,
                  tooltip: 'Add Task',
                  heroTag: "plus",
                  child: const Icon(Icons.add),
                ),
              ]));
        });
  }

  Widget _toWidget(int index) {
    return CheckboxListTile(
        tileColor: const Color.fromARGB(255, 158, 158, 158),
        title: Text(
          _tasks![index].description,
          style: const TextStyle(color: Colors.white),
        ),
        checkColor: Colors.white,
        activeColor: Colors.white,
        selected: _tasks![index].isCompleted,
        value: _tasks![index].isCompleted,
        onChanged: (bool? value) {
          setState(() {
            _tasks![index].isCompleted =
                _tasks![index].isCompleted ? false : true;
          });
        });
  }
}
