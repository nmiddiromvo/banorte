import 'package:flutter/material.dart';
import 'package:saldos/ui/theme/app_colors.dart';

class AppTheme {
  static final Color _lightFocusColor = Colors.black.withOpacity(0.12);
  static final Color _darkFocusColor = Colors.white.withOpacity(0.12);

  static ThemeData lightThemeData =
      themeData(lightColorScheme, _lightFocusColor);
  static ThemeData darkThemeData = themeData(darkColorScheme, _darkFocusColor);

  static ThemeData themeData(ColorScheme colorScheme, Color focusColor) {
    return ThemeData(
        colorScheme: colorScheme,
        canvasColor: colorScheme.background,
        scaffoldBackgroundColor: colorScheme.background,
        highlightColor: Colors.transparent,
        focusColor: focusColor);
  }

  static const ColorScheme lightColorScheme = ColorScheme(
    primary: AppColors.white,
    onPrimary: AppColors.white,
    secondary: AppColors.green,
    onSecondary: AppColors.white,
    error: Colors.redAccent,
    onError: Colors.white,
    background: Colors.white,
    onBackground: Colors.white,
    surface: Colors.white,
    onSurface: Colors.white,
    brightness: Brightness.light,
  );
  static const ColorScheme darkColorScheme = ColorScheme(
    primary: AppColors.black,
    onPrimary: AppColors.white,
    secondary: AppColors.green,
    onSecondary: AppColors.white,
    error: Colors.redAccent,
    onError: Colors.white,
    background: AppColors.black,
    onBackground: Colors.white,
    surface: Colors.white,
    onSurface: Colors.white,
    brightness: Brightness.light,
  );
}

/*
ThemeData(
colorScheme: ColorScheme.fromSeed(
seedColor: const Color.fromARGB(255, 151, 140, 170)),
useMaterial3: true,
)*/
