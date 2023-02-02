import 'package:uuid/uuid.dart';

class Task {
  Task({this.description = '', int? id, int? taskStatus})
      : taskStatus = 0,
        id = id ?? const Uuid().v4().hashCode;
  final String description;
  final int id;
  int taskStatus;

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'description': description,
      'taskStatus': taskStatus,
    };
  }

  @override
  String toString() {
    return 'Task{id: $id, description: $description, taskStatus: $taskStatus}';
  }
}
