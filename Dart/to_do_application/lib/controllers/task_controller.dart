import '../model/task.dart';
import '../services/firestore_storage.dart';
import '../services/local_storage.dart';

class TaskController {
  factory TaskController() => _singleton;

  TaskController._internal();

  static final TaskController _singleton = TaskController._internal();

  Future<List<Task>?> getTasks() {
    return Future.value(FirestoreStorage().getTasks());
  }

  Future<void> insertTask(String description) {
    return Future.value(LocalStorage().insertTask(description));
  }

  Future<void> removeTask(Task task) {
    return Future.value(LocalStorage().removeTask(task));
  }
}
