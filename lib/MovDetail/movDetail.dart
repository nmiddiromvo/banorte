// ignore_for_file: file_names, no_logic_in_create_state
//Participante: Luis Angel Vazquez Diaz
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:los_temerarios/ApiModel/ApiConection.dart';
import '../ApiModel/MovModel.dart';

class MovDetail extends StatefulWidget {
  
  const MovDetail({super.key, required this.movimiento});
  final Movimiento movimiento;

  
  @override
  State createState() {
    return _MovDetailDart(movimiento: movimiento);
  }
}

class _MovDetailDart extends State {
final Movimiento movimiento;
  _MovDetailDart({required this.movimiento});

  void irAtras(BuildContext context) {
    Navigator.pop(context);
  }

  String fechaFormat(String dat){
    return '${DateFormat('dd MMM yyyy\nHH:mm').format(DateTime.parse(dat))}hrs';
  }

  String isMonto(Movimiento mov){
    var monto = '';
    if ( mov.type == 'salida' ){
      monto = '- \$ ${movimiento.amount.toDouble().toStringAsFixed(2)}';
    }
    else{
      monto = '\$ ${movimiento.amount.toDouble().toStringAsFixed(2)}';
    }

    return monto;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        backgroundColor: const Color.fromRGBO(26, 29, 31, 1),
        title: const Text('Transferencia',
            style: TextStyle(
              color: Colors.white,
              fontWeight: FontWeight.bold,
            )),
        centerTitle: true,
        actions: <Widget>[
          IconButton(
            onPressed: () {},
            icon: const Icon(Icons.ios_share_rounded, color: Colors.white),
          )
        ],
      ),
      backgroundColor: const Color.fromRGBO(26, 29, 31, 1),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            const SizedBox(height: 40),
            Row(
              children: [
                const Spacer(),
                OutlinedButton.icon(
                  onPressed: () {},
                  style: OutlinedButton.styleFrom(
                    backgroundColor: Colors.teal,
                  ),
                  icon: const Icon(Icons.check_circle,
                      color: Color.fromRGBO(58, 170, 53, 1)),
                  label: const Text('Completada',
                      style: TextStyle(color: Colors.white)),
                ),
                const Spacer()
              ],
            ),
            const SizedBox(height: 40),
            //MARK: Monto
            Row(
              children: [
                const Spacer(),
                Text( isMonto(movimiento),
                    style: const TextStyle(
                      color: Colors.white,
                      fontWeight: FontWeight.normal,
                      fontSize: 40,
                    )),
                const Spacer(),
              ],
            ),
            const SizedBox(height: 40),
            Row(children: [
              const Spacer(),
              SizedBox(
                width: MediaQuery.of(context).size.width - 50,
                child: Column(
                  children: [
                    
                    Stack(children: [
                      Container(
                        margin: const EdgeInsets.only(top: 48),
                        height: 360,
                        decoration: BoxDecoration(
                          color: const Color.fromRGBO(32, 33, 35, 1),
                          borderRadius: BorderRadius.circular(16.0),
                        ),
                        child: Column(
                          children: [

                          const Row(children: [SizedBox(height: 100)]),
                    //MARK: Nombre del movimiento
                     Row(
                      children: [
                        const Spacer(),
                        Text( movimiento.category,
                            style: const TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                            )),
                        const Spacer()
                      ],
                    ),
                    const Row(children: [SizedBox(height: 40)]),
                    Divider(
                      color: Colors.white.withOpacity(0.1),
                      thickness: 1.24,
                    ),
                    const Row(children: [SizedBox(height: 40)]),
                    //MARK: Datos de la Cuenta Origen
                    const Row(
                      children: [
                        Spacer(),
                        Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('No. de cuenta',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Banco',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('RFC o CURP',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  ))
                            ]),
                        Spacer(),
                        Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('**** 5764',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Santander México',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('OIJM870505SW8',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  ))
                            ]),
                        Spacer(),
                      ],
                    ),
                    const Row(children: [SizedBox(height: 40)]),
                  ],
                        ),
                      ),
                      const Align(
                          alignment: Alignment.topCenter,
                          child: SizedBox(
                            child: CircleAvatar(
                              radius: 60.0,
                              backgroundColor: Colors.white,
                              child: CircleAvatar(
                                backgroundColor: Color.fromRGBO(59, 59, 60, 1),
                                radius: 80.0,
                                child: Icon(
                                  Icons.compare_arrows_rounded,
                                  color: Colors.white,
                                  size: 60,
                                ),
                              ),
                            ),
                          )),
                    ]),
                    // ignore: unnecessary_const
                  ],
                ),
              ),
              const Spacer(),
            ]),
            const SizedBox(height: 20),
            Row(children: [
              const Spacer(),
              Container(
                
                width: MediaQuery.of(context).size.width - 50,
                decoration: BoxDecoration(
                          color: const Color.fromRGBO(32, 33, 35, 1),
                          borderRadius: BorderRadius.circular(16.0),
                        ),
                child: Column(
                  children: [
                    const SizedBox(height: 40),
                    //MARK: Cuenta Destino
                    Row(
                      children: [
                        const SizedBox(width: 30),
                        const Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('Desde',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('No. de cuenta',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Banco',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Fecha',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Concepto',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  ))
                            ]),
                        const Spacer(),
                        Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(movimiento.desde,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text(movimiento.noCuenta,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text(movimiento.bancoDestino,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text(fechaFormat(movimiento.date),
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text(movimiento.concepto,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  ))
                            ]),
                        const Spacer(),
                      ],
                    ),
                    const SizedBox(height: 40),
                    Divider(
                      color: Colors.white.withOpacity(0.1),
                      thickness: 1.24,
                    ),
                    const SizedBox(height: 40),
                    //MARK: Comisiones
                    Row(
                      children: [
                        const SizedBox(width: 30),
                        const Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('Comisión',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('IVA',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                            ]),
                        const Spacer(),
                        Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('\$ ${movimiento.comision.toDouble().toStringAsFixed(2)}',
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text('\$ ${movimiento.iva.toStringAsFixed(2)}',
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                            ]),
                        const Spacer(),
                      ],
                    ),
                    const SizedBox(height: 40),
                    Divider(
                      color: Colors.white.withOpacity(0.1),
                      thickness: 1.24,
                    ),
                    const SizedBox(height: 40),
                    //MARK: Referencia
                    Row(
                      children: [
                        const SizedBox(width: 30),
                        const Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('Folio',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Referencia',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                              SizedBox(height: 20),
                              Text('Clave de rastreo',
                                  style: TextStyle(
                                    color: Color.fromRGBO(255, 255, 255, 0.6),
                                    fontWeight: FontWeight.normal,
                                  )),
                            ]),
                        const Spacer(),
                        Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(movimiento.folio,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text(movimiento.referencia,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                              const SizedBox(height: 20),
                              Text(movimiento.claveRastreo,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.normal,
                                  )),
                            ]),
                        const Spacer(),
                      ],
                    ),
                    const SizedBox(height: 40),
                  ],
                ),
              ),
              const Spacer(),
            ]),
            const SizedBox(height: 10),
            //MARK: CEP
            Row(
              children: [
                TextButton(
                    onPressed: () {},
                    child: const Text('Consulta el comprobante CEP aquí',
                        style: TextStyle(
                          color: Color.fromRGBO(251, 54, 255, 1),
                          fontWeight: FontWeight.bold,
                        ))),
                const Spacer()
              ],
            ),
            const SizedBox(height: 40),
          ],
        ),
      ),
      //MARK: Favorito
      bottomNavigationBar: Expanded(
        child: Container(
            height: 150,
            color: const Color.fromRGBO(26, 29, 31, 1),
            child: Column(
              children: [
                Divider(
                  color: Colors.white.withOpacity(0.1),
                  thickness: 1.24,
                ),
                const SizedBox(height: 20),
                Row(
                  children: [
                    const SizedBox(width: 20),
                    OutlinedButton.icon(
                      onPressed: () {
                        ApiConection().isFavorite(movimiento.id);
                      },
                      icon: const Icon(Icons.star, color: Colors.amber),
                      style: IconButton.styleFrom(
                        backgroundColor: const Color.fromRGBO(32, 33, 35, 1),
                      ),
                      label: const Text('Marcar como favorito',
                          style: TextStyle(color: Colors.white)),
                    )
                  ],
                ),
                const SizedBox(height: 20),
                Divider(
                  color: Colors.white.withOpacity(0.1),
                  thickness: 1.24,
                )
              ],
            )),
      ),
    );
  }
}

//32/33/35
