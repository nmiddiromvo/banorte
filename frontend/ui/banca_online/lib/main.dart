import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:flutter/services.dart' as rootBundle;

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
  final String description;
  final double amount;
  final DateTime dateTime; // Cambiado de 'date' a 'dateTime'
  final String type;
  bool favorite;

  Movement({
    required this.id,
    required this.description,
    required this.amount,
    required this.dateTime, // Cambiado de 'date' a 'dateTime'
    required this.type,
    required this.favorite,
  });

  factory Movement.fromJson(Map<String, dynamic> json) {
    return Movement(
      id: json['id'],
      description: json['description'],
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

    // Cargar datos desde el archivo JSON local
    final String response = await rootBundle.rootBundle.loadString('assets/mock_data.json');
    final List<dynamic> data = json.decode(response);

    setState(() {
      movements = data.map((json) => Movement.fromJson(json)).toList();
      isLoading = false;
    });
  }

  Future<void> deleteMovement(int id) async {
    setState(() {
      movements.removeWhere((movement) => movement.id == id);
    });
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Movement deleted')));
  }

  Future<void> toggleFavorite(int id) async {
    setState(() {
      Movement movement = movements.firstWhere((movement) => movement.id == id);
      movement.favorite = !movement.favorite;
    });
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Favorite status updated')));
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
                      Text('${movement.dateTime.toLocal()}'), // Mostrar la fecha y hora
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
