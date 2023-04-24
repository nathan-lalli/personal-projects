import 'package:flutter_test/flutter_test.dart';
import 'package:to_do_application/main.dart';

void main() {
  testWidgets('MyApp should render HomePage', (WidgetTester tester) async {
    // Build the MyApp widget.
    await tester.pumpWidget(const MyApp());

    // Verify that the initial route is the HomePage.
    expect(find.text('Welcome to the To Do Application'), findsOneWidget);
  });
}
