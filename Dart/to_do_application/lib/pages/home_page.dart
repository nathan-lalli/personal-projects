// I estimated it to take about an hour to 2 hours to complete this program.
// I actually spent about 7 or 8 hours on and off this program because of the
// Issues that I was having with SQLite.

import 'package:flutter/material.dart';
import 'package:to_do_application/services/local_storage.dart';

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
                actions: <Widget>[
                  Visibility(
                      visible: checkSelection(),
                      child: IconButton(
                        icon: const Icon(
                          Icons.delete_forever_outlined,
                        ),
                        onPressed: () {
                          // Delete selections
                          setState(() {
                            for (int i = 0; i < _tasks!.length; i++) {
                              if (_tasks![i].taskStatus == 1) {
                                TaskController().removeTask(_tasks![i]);
                                _tasks!.removeAt(i);
                              }
                            }
                          });
                        },
                      ))
                ],
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
                    Navigator.of(context).pushReplacementNamed("/new_task");
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
    bool flag = false;
    if (_tasks![index].taskStatus == 0) {
      flag = false;
    } else {
      flag = true;
    }

    return CheckboxListTile(
        tileColor: const Color.fromARGB(255, 158, 158, 158),
        title: Text(
          _tasks![index].description,
          style: const TextStyle(color: Colors.white),
        ),
        checkColor: Colors.black,
        activeColor: Colors.white,
        selected: flag,
        value: flag,
        onChanged: (bool? value) {
          setState(() {
            _tasks![index].taskStatus = flag ? 0 : 1;
          });
        });
  }

  bool checkSelection() {
    for (int i = 0; i < _tasks!.length; i++) {
      if (_tasks![i].taskStatus == 1) {
        return true;
      }
    }
    return false;
  }
}
