import 'package:saldos/account/data/models/mov_response_dto.dart';

abstract interface class AccountDatasource {
  Future<List<MovResponseDto>> getMovements();
}
