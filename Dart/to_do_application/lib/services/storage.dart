import '../model/task.dart';

abstract class Storage {
  Future<List<Task>> getTasks();

  Future<void> insertTask(String description, int taskStatus);

  Future<void> removeTask(Task task);
}
