import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import '../model/task.dart';
import 'storage.dart';

class LocalStorage implements Storage {
  factory LocalStorage() => _singleton;

  LocalStorage._internal();

  static final _singleton = LocalStorage._internal();
  static const _tasksTable = 'Task';
  Database? _cachedDatabase;

  Future<Database> get database async {
    if (_cachedDatabase != null) {
      return _cachedDatabase!;
    }

    final name = join(await getDatabasesPath(), 'todo.db');
    // await deleteDatabase(name);

    _cachedDatabase = await openDatabase(
      name,
      onCreate: _onCreate,
      version: 1,
    );

    return _cachedDatabase!;
  }

  static Future<void> _onCreate(Database db, int version) async {
    await db.execute('''
CREATE TABLE $_tasksTable (
  task_id TEXT PRIMARY KEY,
  description TEXT NOT NULL
);
''');
    return db.execute('''
INSERT INTO $_tasksTable (task_id, description)
VALUES ("fd27cef2-7276-11ed-a1eb-0242ac120002", "task 1");
''');
  }

  @override
  Future<List<Task>> getTasks() async {
    final db = await database;
    // TODO
    return [];
  }

  @override
  Future<Task> insertTask(String description) async {
    final db = await database;
    // TODO
    return Task();
  }

  ///
  /// @return: the number of tasks removed
  ///
  @override
  Future<void> removeTask(Task task) async {
    final db = await database;
    // TODO
  }
}
