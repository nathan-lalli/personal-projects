import '../model/task.dart';
import '../services/fake_data.dart';

class TaskController {
  factory TaskController() => _singleton;

  TaskController._internal();

  static final TaskController _singleton = TaskController._internal();

  Future<List<Task>> getTasks() {
    return Future.value(FakeData().getTasks());
  }
}
