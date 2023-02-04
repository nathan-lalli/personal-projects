import 'package:cloud_firestore/cloud_firestore.dart';

import '../model/task.dart';
import 'storage.dart';

class FirestoreStorage implements Storage {
  CollectionReference tasks = FirebaseFirestore.instance.collection('tasks');

  @override
  Future<List<Task>?> getTasks() async {
    List<Task>? taskList = [];
    QuerySnapshot list = await tasks.get();
    List<Map<String, dynamic>> result = list.docs
        .map((doc) => doc.data())
        .cast<Map<String, dynamic>>()
        .toList();
    taskList = List.generate(result.length, (i) {
      return Task(
        id: result[i]['task_id'],
        description: result[i]['description'],
        taskStatus: result[i]['taskStatus'],
      );
    });

    return taskList;
  }

  @override
  Future<Task> insertTask(String description) async {
    QuerySnapshot list = await tasks.get();
    List<Map<String, dynamic>> result = list.docs
        .map((doc) => doc.data())
        .cast<Map<String, dynamic>>()
        .toList();
    const int status = 0;
    int id = result.last['task_id'] + 1;

    final newTask = {
      "task_id": id,
      "description": description,
      "taskStatus": status,
    };

    await tasks.add(newTask);

    return Task(
      id: id,
      description: description,
      taskStatus: status,
    );
  }

  @override
  Future<void> removeTask(Task task) async {
    int deleteId = task.id;
    QuerySnapshot list = await tasks.get();
    List<Map<String, dynamic>> result = list.docs
        .map((doc) => doc.data())
        .cast<Map<String, dynamic>>()
        .toList();
    for (int i = 0; i < result.length; i++) {
      if (result[i]['task_id'] == deleteId) {
        await tasks.doc(list.docs[i].id).delete();
      }
    }

    return Future.value();
  }
}
