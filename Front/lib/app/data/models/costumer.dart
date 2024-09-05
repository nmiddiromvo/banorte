class Costumer {


    final String id;
    final String firstName;
    final String lastName;



  Costumer({ required this.id, required this.firstName, required this.lastName });

  factory Costumer.fromJson(Map<String, dynamic> json) {
    return Costumer(
      id: json['_id'] as String,
      firstName: json['firstName'] as String,
      lastName: json['lastName'] as String,
    );
  }

  @override
  String toString() {
    return 'Costumer{id: $id, firstName: $firstName, lastName: $lastName}';
  }
}