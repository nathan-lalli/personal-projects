//import 'package:uuid/uuid.dart';

class Task {
  Task({this.description = '', this.id = 0, this.taskStatus = 0});
  //static const _uuid = Uuid();
  final String description;
  final int id;
  int taskStatus;

  Map<String, dynamic> toMap() {
    return {
      'task_id': id,
      'description': description,
      'taskStatus': taskStatus,
    };
  }

  @override
  String toString() {
    return 'Task{task_id: $id, description: $description, taskStatus: $taskStatus}';
  }
}
