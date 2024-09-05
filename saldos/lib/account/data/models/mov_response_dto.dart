import 'dart:convert';

List<MovResponseDto> movResponseDtoFromJson(String str) => List<MovResponseDto>.from(json.decode(str).map((x) => MovResponseDto.fromJson(x)));

String movResponseDtoToJson(List<MovResponseDto> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class MovResponseDto {
  double? monto;
  DateTime? fecha;
  String? tipo;
  String? concepto;
  bool? favorito;

  MovResponseDto({
    this.monto,
    this.fecha,
    this.tipo,
    this.concepto,
    this.favorito,
  });

  factory MovResponseDto.fromJson(Map<String, dynamic> json) => MovResponseDto(
    monto: json["monto"]?.toDouble(),
    fecha: json["fecha"] == null ? null : DateTime.parse(json["fecha"]),
    tipo: json["tipo"],
    concepto: json["concepto"],
    favorito: json["favorito"],
  );

  Map<String, dynamic> toJson() => {
    "monto": monto,
    "fecha": fecha?.toIso8601String(),
    "tipo": tipo,
    "concepto": concepto,
    "favorito": favorito,
  };
}
