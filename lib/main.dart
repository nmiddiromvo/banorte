import 'package:flutter/material.dart';
import 'package:los_temerarios/ApiModel/MovModel.dart';
import 'MovDetail/movDetail.dart';
//import 'Movs/movs.dart.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    /*
    
    "id": 1,
        "amount": 2500,
        "date": "2024-09-05T06:00:00.000+00:00",
        "type": "salida",
        "favorite": false,
        "category": "COMIDA",
        "desde": "Cuenta de depósito",
        "no_cuenta": "465184982",
        "banco_destino": "NU BANK",
        "concepto": "DOROTEANDO",
        "comision": 15,
        "iva": 1.5,
        "folio": "15762",
        "referencia": "420",
        "clave_rastreo": "098723459081234"
    */
    Movimiento ejemplo = Movimiento(
        amount: 2500,
        date: '2024-09-05T06:00:00.000+00:00',
        type: "salida",
        favorite: false,
        category: "COMIDA",
        desde: 'Cuenta de depósito',
        noCuenta: '465184982',
        bancoDestino: 'NU BANK',
        concepto: 'DOROTEANDO',
        comision: 15,
        iva: 1.5,
        folio: '15762',
        referencia: '420',
        claveRastreo: '098723459081234', id: 1);

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
            seedColor: const Color.fromARGB(255, 151, 140, 170)),
        useMaterial3: true,
      ),
      home: MovDetail(movimiento: ejemplo),
    );
  }
}
