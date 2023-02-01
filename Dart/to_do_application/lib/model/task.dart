import 'package:uuid/uuid.dart';

class Task {
  Task({this.description = '', String? id, required taskStatus})
      : isCompleted = false,
        id = id ?? _uuid.v1();
  bool isCompleted;
  static const _uuid = Uuid();
  final String description;
  final String id;
}
