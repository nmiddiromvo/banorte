import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
  runApp(BankApp());
}

class BankApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Bank Movements',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MovementsScreen(),
    );
  }
}

class Movement {
  final int id;
  final String description; // Nuevo campo añadido
  final double amount;
  final DateTime dateTime; // Cambiado de 'date' a 'dateTime'
  final String type;
  bool favorite;

  Movement({
    required this.id,
    required this.description, // Nuevo campo
    required this.amount,
    required this.dateTime, // Cambiado de 'date' a 'dateTime'
    required this.type,
    required this.favorite,
  });

  factory Movement.fromJson(Map<String, dynamic> json) {
    return Movement(
      id: json['id'],
      description: json['description'], // Nuevo campo desde el JSON
      amount: json['amount'],
      dateTime: DateTime.parse(json['dateTime']), // Cambiado de 'date' a 'dateTime'
      type: json['type'],
      favorite: json['favorite'],
    );
  }
}

class MovementsScreen extends StatefulWidget {
  @override
  _MovementsScreenState createState() => _MovementsScreenState();
}

class _MovementsScreenState extends State<MovementsScreen> {
  List<Movement> movements = [];
  bool isLoading = false;

  @override
  void initState() {
    super.initState();
    fetchMovements();
  }

  Future<void> fetchMovements() async {
    setState(() {
      isLoading = true;
    });

    final response = await http.get(Uri.parse('http://localhost:8080/api/transactions'));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      setState(() {
        movements = data.map((json) => Movement.fromJson(json)).toList();
        isLoading = false;
      });
    } else {
      // Handle errors
      setState(() {
        isLoading = false;
      });
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Failed to load movements')));
    }
  }

  Future<void> deleteMovement(int id) async {
    final response = await http.delete(Uri.parse('https://api.example.com/movements/$id'));

    if (response.statusCode == 200) {
      setState(() {
        movements.removeWhere((movement) => movement.id == id);
      });
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Movement deleted')));
    } else {
      // Handle errors
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Failed to delete movement')));
    }
  }

  Future<void> toggleFavorite(int id) async {
    final response = await http.patch(Uri.parse('https://api.example.com/movements/$id/favorite'));

    if (response.statusCode == 200) {
      setState(() {
        Movement movement = movements.firstWhere((movement) => movement.id == id);
        movement.favorite = !movement.favorite;
      });
    } else {
      // Handle errors
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Failed to update favorite status')));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Bank Movements'),
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: movements.length,
              itemBuilder: (context, index) {
                final movement = movements[index];
                return ListTile(
                  title: Text('${movement.type} - \$${movement.amount.toStringAsFixed(2)}'),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(movement.description), // Muestra la descripción
                      Text('${movement.dateTime.toLocal()}'), // Muestra la fecha y hora
                    ],
                  ),
                  trailing: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                        icon: Icon(
                          movement.favorite ? Icons.favorite : Icons.favorite_border,
                          color: movement.favorite ? Colors.red : null,
                        ),
                        onPressed: () => toggleFavorite(movement.id),
                      ),
                      IconButton(
                        icon: Icon(Icons.delete),
                        onPressed: () => deleteMovement(movement.id),
                      ),
                    ],
                  ),
                );
              },
            ),
    );
  }
}
