import 'package:firebase_core/firebase_core.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

void main() {
  // Initialize Firebase app before running tests.
  setUpAll(() async {
    await Firebase.initializeApp();
  });

  test('Firebase Firestore should add and read data', () async {
    // Get a reference to the Firestore collection.
    final collection = FirebaseFirestore.instance.collection('test');

    // Add a new document to the collection.
    await collection
        .add({'Description': 'Test of firebase', 'Purpose': 'Testing'});

    // Retrieve the document from the collection.
    final snapshot = await collection.get();

    // Verify that the document exists and contains the correct data.
    expect(snapshot.docs.length, equals(1));
    expect(snapshot.docs.first.get('Description'), equals('Test of firebase'));
    expect(snapshot.docs.first.get('Purpose'), equals('Testing'));
  });
}
