import 'package:uuid/uuid.dart';

class Task {
  Task({this.description = '', String? id, int? taskStatus})
      : taskStatus = 0,
        id = id ?? _uuid.v1();
  static const _uuid = Uuid();
  final String description;
  final String id;
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
