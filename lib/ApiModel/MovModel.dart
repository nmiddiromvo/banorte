// ignore_for_file: file_names

class Movimiento {
    int id;
    int amount;
    String date;
    String type;
    bool favorite;
    String category;
    String desde;
    String noCuenta;
    String bancoDestino;
    String concepto;
    int comision;
    double iva;
    String folio;
    String referencia;
    String claveRastreo;

    Movimiento({
        required this.id,
        required this.amount,
        required this.date,
        required this.type,
        required this.favorite,
        required this.category,
        required this.desde,
        required this.noCuenta,
        required this.bancoDestino,
        required this.concepto,
        required this.comision,
        required this.iva,
        required this.folio,
        required this.referencia,
        required this.claveRastreo,
    });

    factory Movimiento.fromJson(Map<String, dynamic> json) => Movimiento(
        id: json["id"],
        amount: json["amount"],
        date: json["date"],
        type: json["type"],
        favorite: json["favorite"],
        category: json["category"],
        desde: json["desde"],
        noCuenta: json["no_cuenta"],
        bancoDestino: json["banco_destino"],
        concepto: json["concepto"],
        comision: json["comision"],
        iva: json["iva"]?.toDouble(),
        folio: json["folio"],
        referencia: json["referencia"],
        claveRastreo: json["clave_rastreo"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "amount": amount,
        "date": date,
        "type": type,
        "favorite": favorite,
        "category": category,
        "desde": desde,
        "no_cuenta": noCuenta,
        "banco_destino": bancoDestino,
        "concepto": concepto,
        "comision": comision,
        "iva": iva,
        "folio": folio,
        "referencia": referencia,
        "clave_rastreo": claveRastreo,
    };
}
