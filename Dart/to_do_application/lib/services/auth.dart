import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class Auth {
  final _auth = FirebaseAuth.instance;

  Future<String?> signInWithEmailAndPassword(
      {required String email, required String password}) async {
    try {
      await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );
      return null;
    } on FirebaseAuthException catch (e) {
      return _parseSignInAuthException(e);
    }
  }

  Future<String?> createAccountWithEmailAndPassword(
      {required String email, required String password}) async {
    try {
      final db = FirebaseFirestore.instance;
      final id = await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );
      CollectionReference users = db.collection('users');

      users.doc(id.user!.uid).set({});
      return null;
    } on FirebaseAuthException catch (e) {
      return _parseCreateAccountAuthException(e);
    }
  }

  String? getUserId() {
    final user = _auth.currentUser;
    if (user != null) {
      return user.uid;
    } else {
      return null;
    }
  }

  bool isSignedIn() {
    final user = _auth.currentUser;
    if (user != null) {
      return true;
    } else {
      return false;
    }
  }

  void signOut() {}

  String _parseSignInAuthException(FirebaseAuthException exception) {
    switch (exception.code) {
      case 'invalid-email':
        return 'Email address is not formatted correctly';
      case 'user-not-found':
      case 'wrong-password':
      case 'user-disabled':
        return 'Invalid username or password';
      case 'network-request-failed':
        return 'Please ensure you are online and try again';
      case 'too-many-requests':
      case 'operation-not-allowed':
      default:
        return 'An unknown error occurred';
    }
  }

  String _parseCreateAccountAuthException(FirebaseAuthException exception) {
    switch (exception.code) {
      case 'weak-password':
        return 'Password is too weak';
      case 'invalid-email':
        return 'Email address is not formatted correctly';
      case 'email-already-in-use':
        return 'This email address already exists';
      case 'network-request-failed':
        return 'Please ensure you are online and try again';
      default:
        return 'An unknown error occurred';
    }
  }
}
