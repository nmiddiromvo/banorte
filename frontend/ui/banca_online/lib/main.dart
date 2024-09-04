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
      title: 'Movimientos Bancarios',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MovementsScreen(),
    );
  }
}

class Movement {
  final int id;
  final double amount;
  final DateTime date;
  final String type;
  bool favorite;

  Movement({required this.id, required this.amount, required this.date, required this.type, required this.favorite});

  factory Movement.fromJson(Map<String, dynamic> json) {
    return Movement(
      id: json['id'],
      amount: json['amount'],
      date: DateTime.parse(json['date']),
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
        title: Text('Movimientos'),
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: movements.length,
              itemBuilder: (context, index) {
                final movement = movements[index];
                return ListTile(
                  title: Text('${movement.type} - \$${movement.amount.toStringAsFixed(2)}'),
                  subtitle: Text('${movement.date.toLocal()}'),
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
