import 'dart:convert';

import 'package:helloflutter/app/data/models/account.dart';
import 'package:http/http.dart' as http;

class ApiService {
  final String apiUrl = "http://192.168.0.7:3000/api";

  Future<Account> getAccountById(String id) async {
    final response = await http.get(Uri.parse('$apiUrl/$id'));

    if (response.statusCode == 200) {
      return Account.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load an account');
    }
  }
}
