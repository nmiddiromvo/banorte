import 'package:flutter/cupertino.dart';

void main() {
  runApp(const CupertinoApp(
    title: 'Navigation Basics',
    home: AccountRoute(),
  ));
}

class AccountRoute extends StatelessWidget {
  const AccountRoute({super.key});

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      navigationBar: const CupertinoNavigationBar(
        middle: Text('Cuenta ahorro'),
      ),
      child: Center(
        child: CupertinoButton(
          child: const Text('Open route'),
          onPressed: () {
            Navigator.pushNamed(context,
                '/details',arguments:{"id":25});

          },
        ),
      ),
    );
  }
}
