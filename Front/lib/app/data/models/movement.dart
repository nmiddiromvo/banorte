import 'dart:ffi';
import 'package:big_decimal/big_decimal.dart';

class Movement {
  final String id;
  final BigDecimal amount;
  final DateTime movementDate;
  final String type;
  final Bool isFavorite;

  Movement(
      {required this.id,
      required this.amount,
      required this.movementDate,
      required this.type,
      required this.isFavorite});

  factory Movement.fromJson(Map<String, dynamic> json) {
    return Movement(
      id: json['_id'] as String,
      amount: json['amount'] as BigDecimal,
      movementDate: json['movementDate'] as DateTime,
      type: json['type'] as String,
      isFavorite: json['isFavorite'] as Bool,
    );
  }

  @override
  String toString() {
    return 'Movement{id: $id, amount: $amount, movementDate: $movementDate, type: $type, isFavorite: $isFavorite}';
  }
}
