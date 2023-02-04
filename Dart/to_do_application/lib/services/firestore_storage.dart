import 'package:cloud_firestore/cloud_firestore.dart';

import '../model/task.dart';
import 'storage.dart';

class FirestoreStorage implements Storage {
  FirebaseFirestore db = FirebaseFirestore.instance;
  CollectionReference tasks = FirebaseFirestore.instance.collection('tasks');

  @override
  Future<List<Task>?> getTasks() async {
    List<Task>? result = [];
    QuerySnapshot list = await tasks.get();
    result = list.docs.map((doc) => doc.data()).cast<Task>().toList();

    return result;
  }

  @override
  Future<Task> insertTask(String description) async {
    return Task(id: -1);
  }

  @override
  Future<void> removeTask(Task task) {
    return Future.value();
  }
}
