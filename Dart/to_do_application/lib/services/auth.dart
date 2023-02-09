import 'package:firebase_auth/firebase_auth.dart';

class Auth {
  final _auth = FirebaseAuth.instance;

  Future<String?> signInWithEmailAndPassword(
      {required String email, required String password}) async {
    try {
      // TODO use _auth to create an account
      return null;
    } on FirebaseAuthException catch (e) {
      return _parseSignInAuthException(e);
    }
  }

  Future<String?> createAccountWithEmailAndPassword(
      {required String email, required String password}) async {
    try {
      // TODO use _auth to create an account
      return null;
    } on FirebaseAuthException catch (e) {
      return _parseCreateAccountAuthException(e);
    }
  }

  String? getUserId() {
    // TODO
  }

  bool isSignedIn() {
    // TODO
    return false;
  }

  void signOut() {
    // TODO
  }

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
