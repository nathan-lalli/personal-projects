import 'package:uuid/uuid.dart';

class Task {
  Task({this.description = '', String? id, bool? taskStatus})
      : taskStatus = false,
        id = id ?? _uuid.v1();
  static const _uuid = Uuid();
  final String description;
  final String id;
  final bool taskStatus;

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
