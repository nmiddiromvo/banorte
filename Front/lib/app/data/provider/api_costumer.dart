import 'dart:async';
import 'dart:convert';

import 'package:helloflutter/app/data/models/costumer.dart';
import 'package:http/http.dart' as http;

class ApiService {
  final String apiUrl = "http://192.168.0.7:3000/api";

  Future<Costumer> getCostumerById(String id) async {
    final response = await http.get(Uri.parse('$apiUrl/$id'));

    if (response.statusCode == 200) {
      return Costumer.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load a costumer');
    }
  }
}
