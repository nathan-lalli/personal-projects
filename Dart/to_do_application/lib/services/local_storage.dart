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
  task_id INTEGER PRIMARY KEY AUTOINCREMENT,
  description TEXT NOT NULL,
  taskStatus BOOLEAN
);
''');
    return db.execute('''
INSERT INTO $_tasksTable (description, taskStatus)
VALUES ("task 1", 0);
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
  Future<void> insertTask(String description, bool taskStatus) async {
    final db = await database;

    final value = {
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
