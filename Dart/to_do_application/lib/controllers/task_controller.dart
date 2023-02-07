import '../model/task.dart';
import '../services/firestore_storage.dart';
import '../services/OLDlocal_storage.dart';

class TaskController {
  factory TaskController() => _singleton;

  TaskController._internal();

  static final TaskController _singleton = TaskController._internal();

  Future<List<Task>?> getTasks() {
    return Future.value(FirestoreStorage().getTasks());
  }

  Future<Task> insertTask(String description) {
    return Future.value(FirestoreStorage().insertTask(description));
  }

  Future<void> removeTask(Task task) {
    return Future.value(FirestoreStorage().removeTask(task));
  }
}
