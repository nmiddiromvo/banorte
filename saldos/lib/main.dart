import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:saldos/account/presenter/ui/account_page.dart';
import 'package:saldos/ui/theme/app_theme.dart';

void main() {
  runApp(const ProviderScope(child: MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Movimientos',
      themeMode: ThemeMode.dark,
      theme: AppTheme.darkThemeData,
      darkTheme: AppTheme.darkThemeData,
      home: const AccountPage(),
    );
  }
}
