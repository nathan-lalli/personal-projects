import 'package:flutter_test/flutter_test.dart';
import 'package:to_do_application/pages/home_page.dart';

void main() {
  testWidgets('MyApp should render HomePage', (WidgetTester tester) async {
    // Build the MyApp widget.
    await tester.pumpWidget(const HomePage());

    // Verify that the initial route is the HomePage.
    expect(find.text('Todo'), findsOneWidget);
  });
}
