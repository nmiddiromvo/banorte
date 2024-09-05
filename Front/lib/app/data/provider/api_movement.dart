import 'dart:async';
import 'dart:convert';

import 'package:helloflutter/app/data/models/movement.dart';
import 'package:http/http.dart' as http;

class ApiService {
  final String apiUrl = "http://192.168.0.7:3000/api";

  Future<List<Movement>> getMovements() async {
    http.Response res = await http.get(Uri.parse(apiUrl));

    if (res.statusCode == 200) {
      List<dynamic> body = jsonDecode(res.body);
      return body.map((dynamic item) => Movement.fromJson(item)).toList();
    } else {
      throw "Failed to load movements list";
    }
  }

  Future<Movement> getMovementById(String id) async {
    final response = await http.get(Uri.parse('$apiUrl/$id'));

    if (response.statusCode == 200) {
      return Movement.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load a movement');
    }
  }

  Future<Movement> updateMovement(String id, Movement movements) async {
    Map data = {
      'amount': movements.amount,
      'movementDate': movements.movementDate,
      'type': movements.type,
      'isFavorite': movements.isFavorite
    };

    final http.Response response = await http.put(
      Uri.parse('$apiUrl/$id'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(data),
    );
    if (response.statusCode == 200) {
      return Movement.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to update a movement');
    }
  }

  Future<bool> deleteMovement(String id) async {
    http.Response res = await http.delete(Uri.parse('$apiUrl/$id'));

    if (res.statusCode == 200) {
      return true;
    } else {
      throw "Failed to delete a movement.";
    }
  }
}
