// Flutter: 

import 'package:flutter/material.dart';



void main() {

  runApp(const MyApp());

}



class MyApp extends StatelessWidget {

  const MyApp({super.key});



  @override

  Widget build(BuildContext context) {

    return MaterialApp(

      theme: ThemeData.dark(),

      home: const AccountRoute(),

    );

  }

}



class AccountRoute extends StatelessWidget {

  const AccountRoute({super.key});

  get onPressed => null;



  @override

  Widget build(BuildContext context) {

    return Scaffold(

      appBar: AppBar(

        backgroundColor: Colors.black,

        leading: const Icon(Icons.arrow_back),

        actions: const [

          Icon(Icons.visibility_off),

        ],

      ),

      body: Padding(

        padding: const EdgeInsets.all(16.0),

        child: Column(

          crossAxisAlignment: CrossAxisAlignment.start,

          children: [

            const Text(

              'Cuenta ahorro',

              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),

            ),

            const SizedBox(height: 8),

            const Text(

              'Saldo disponible en **** 5764',

              style: TextStyle(color: Colors.grey),

            ),

            const SizedBox(height: 8),

            const Text(

              '\$ 32,890.00',

              style: TextStyle(fontSize: 36, fontWeight: FontWeight.bold),

            ),

            const SizedBox(height: 24),

            Row(

              mainAxisAlignment: MainAxisAlignment.spaceBetween,

              children: [

                _buildButton(Icons.file_copy, 'E. de cuenta'),

                _buildButton(Icons.info, 'Detalles'),

                _buildButton(Icons.settings, 'Configurar', border: true),

              ],

            ),

            const SizedBox(height: 24),

            _buildSearchBar(),

            const SizedBox(height: 24),

            _buildTransactionSection('Abril', [

              _buildTransactionItem(

                Icons.swap_horiz,

                'Para Margarita Ortiz',

                '15 abr 2021, 14:58 hrs',

                '- \$ 13,000.00',

                status: 'En proceso',

                statusColor: Colors.purple,

              ),



            ]),

            const SizedBox(height: 24),

           ],

        ),

      ),

    );

  }



  Widget _buildButton(IconData icon, String label, {bool border = false}) {

    return Container(

      padding: const EdgeInsets.all(16),

      decoration: BoxDecoration(

        color: Colors.grey[850],

        borderRadius: BorderRadius.circular(8),

        border: border ? Border.all(color: Colors.purple) : null,

      ),

      child: Column(

        children: [

          Icon(icon, size: 32),

          const SizedBox(height: 8),

          Text(label),

        ],

      ),

    );

  }



  Widget _buildSearchBar() {

    return Container(

      padding: const EdgeInsets.symmetric(horizontal: 16),

      decoration: BoxDecoration(

        color: Colors.grey[850],

        borderRadius: BorderRadius.circular(8),

      ),

      child: const Row(

        children: [

          Icon(Icons.search, color: Colors.grey),

          SizedBox(width: 8),

          Expanded(

            child: TextField(

              decoration: InputDecoration(

                hintText: 'Buscar movimiento',

                border: InputBorder.none,

              ),

            ),

          ),

          Icon(Icons.tune, color: Colors.grey),

        ],

      ),

    );

  }



  Widget _buildTransactionSection(String month, List<Widget> transactions) {

    return Column(

      crossAxisAlignment: CrossAxisAlignment.start,

      children: [

        Text(

          month,

          style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),

        ),

        const SizedBox(height: 16),

        ...transactions,

      ],

    );

  }



  Widget _buildTransactionItem(IconData icon, String title, String date, String amount,

      {String? status, Color? statusColor, Color? amountColor}) {

    return Padding(

      padding: const EdgeInsets.symmetric(vertical: 8.0),

      child: Row(

        mainAxisAlignment: MainAxisAlignment.spaceBetween,

        children: [

          Row(

            children: [

              Container(

                padding: const EdgeInsets.all(8),

                decoration: BoxDecoration(

                  color: Colors.grey[850],

                  borderRadius: BorderRadius.circular(8),

                ),

                child: Icon(icon, size: 24),

              ),

              const SizedBox(width: 16),

              Column(

                crossAxisAlignment: CrossAxisAlignment.start,

                children: [

                  Text(title),

                  Text(

                    date,

                    style: const TextStyle(color: Colors.grey, fontSize: 12),

                  ),

                ],

              ),

            ],

          ),

          Column(

            crossAxisAlignment: CrossAxisAlignment.end,

            children: [

              Text(

                amount,

                style: TextStyle(

                  color: amountColor ?? Colors.red,

                  fontSize: 16,

                ),

              ),

              if (status != null)

                Text(

                  status,

                  style: TextStyle(color: statusColor, fontSize: 12),

                ),

            ],

          ),

        ],

      ),

    );

  }

} 

 