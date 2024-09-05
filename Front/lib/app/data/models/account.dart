import 'package:big_decimal/big_decimal.dart';

class Account {
   final String id;
   final BigDecimal balance;
   final String type;


  Account({ required this.id, required this.balance, required this.type });

  factory Account.fromJson(Map<String, dynamic> json) {
    return Account(
      id: json['_id'] as String,
      balance: json['balance'] as BigDecimal,
      type: json['type'] as String,
    );
  }

  @override
  String toString() {
    return 'Account{id: $id, balance: $balance, type: $type}';
  }
}