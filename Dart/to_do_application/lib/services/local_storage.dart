import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import '../model/task.dart';
import 'storage.dart';
import 'package:uuid/uuid.dart';

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
  description TEXT NOT NULL,
  taskStatus INTEGER
);
''');
    return db.execute('''
INSERT INTO $_tasksTable (task_id, description, taskStatus)
VALUES ("fd27cef2-7276-11ed-a1eb-0242ac120002", "task 1", 0);
''');
  }

  @override
  Future<List<Task>> getTasks() async {
    final db = await database;

    final List<Map<String, dynamic>> result = await db.query(_tasksTable);

    return List.generate(result.length, (i) {
      return Task(
        id: result[i]['id'],
        description: result[i]['description'],
        taskStatus: result[i]['taskStatus'],
      );
    });
  }

  @override
  Future<void> insertTask(String description, int taskStatus) async {
    final db = await database;

    const taskId = Uuid();

    final value = {
      'id': taskId.v1(),
      'description': description,
      'taskStatus': taskStatus,
    };

    await db.insert(_tasksTable, value);
  }

  ///
  /// @return: the number of tasks removed
  ///
  @override
  Future<void> removeTask(Task task) async {
    final db = await database;

    await db.delete(
      _tasksTable,
      where: 'id = ?',
      whereArgs: [task.id],
    );
  }
}
