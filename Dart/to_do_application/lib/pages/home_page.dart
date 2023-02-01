import 'package:flutter/material.dart';

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
            appBar: AppBar(title: const Text('Todo')),
            body: ListView.separated(
              itemBuilder: (_, index) => _toWidget(index),
              separatorBuilder: (_, __) => const Divider(),
              itemCount: _tasks!.length,
            ),
          );
        });
  }

  Widget _toWidget(int index) {
    return const SizedBox();
  }
}
