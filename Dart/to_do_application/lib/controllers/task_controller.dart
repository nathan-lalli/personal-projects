import '../model/task.dart';
import '../services/local_storage.dart';

class TaskController {
  factory TaskController() => _singleton;

  TaskController._internal();

  static final TaskController _singleton = TaskController._internal();

  Future<List<Task>> getTasks() {
    return Future.value(LocalStorage().getTasks());
  }

  Future<void> insertTask(String description, bool taskStatus) {
    return Future.value(LocalStorage().insertTask(description, taskStatus));
  }

  Future<void> removeTask(Task task) {
    return Future.value(LocalStorage().removeTask(task));
  }
}
