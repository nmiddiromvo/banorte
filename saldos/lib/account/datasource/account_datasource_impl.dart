import 'dart:convert';

import 'package:saldos/account/data/datasource/account_datasource.dart';
import 'package:http/http.dart' as http;
import 'package:saldos/account/data/models/mov_response_dto.dart';

class AccountDatasourceImpl implements AccountDatasource {
  String GET_MOVEMENTS = 'http://localhost:8086/api/movimientos/lista';

  @override
  Future<List<MovResponseDto>> getMovements() async {
    var client = http.Client();
    var uri = Uri.parse(GET_MOVEMENTS);
    var response = await client.get(uri);
    if (response.statusCode == 200) {
      List<MovResponseDto> list = movResponseDtoFromJson(
          const Utf8Decoder().convert(response.bodyBytes));
      list.map((item) => print(item.concepto));
      return list.toList();
    }
    return [];
  }
}
