import 'package:cloud_firestore/cloud_firestore.dart';
import '../controllers/auth_controller.dart';
import '../model/task.dart';
import 'storage.dart';

class FirestoreStorage implements Storage {
  static String? userId = AuthController().getUserId();
  // CollectionReference tasks = FirebaseFirestore.instance.collection('tasks');
  CollectionReference tasks = FirebaseFirestore.instance
      .collection('user')
      .doc(userId)
      .collection('tasks');

  @override
  Future<List<Task>?> getTasks() async {
    List<Task>? taskList = [];
    final db = FirebaseFirestore.instance;
    final userId = AuthController().getUserId();
    if (userId == null) {
      return [];
    }
    final docs =
        await db.collection('user').doc(userId).collection('tasks').get();
    List<Map<String, dynamic>> result = docs.docs
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
    final db = FirebaseFirestore.instance;
    final userId = AuthController().getUserId();
    final docs =
        await db.collection('users').doc(userId).collection('tasks').get();
    List<Map<String, dynamic>> result = docs.docs
        .map((doc) => doc.data())
        .cast<Map<String, dynamic>>()
        .toList();
    const int status = 0;
    int id;
    try {
      id = result.last['task_id'] + 1;
    } on StateError {
      id = 0;
    }

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
