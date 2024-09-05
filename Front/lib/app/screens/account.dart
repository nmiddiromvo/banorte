import 'package:flutter/cupertino.dart';
import '';

void main() {
  runApp(const CupertinoApp(
    title: 'Navigation Basics',
    home: DetailsRoute(),
  ));
}



class DetailsRoute extends StatelessWidget {
  const DetailsRoute({super.key});

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      navigationBar: const CupertinoNavigationBar(
        middle: Text('Details'),
      ),
      child: Center(
        child: CupertinoButton(
          onPressed: () {
            Navigator.pop(context);
          },
          child: const Text('Go back!'),
        ),
      ),
    );
  }
}