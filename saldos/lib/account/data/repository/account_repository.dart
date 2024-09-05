import 'package:saldos/account/data/models/mov_response_dto.dart';

abstract class AccountRepository {
  Future<List<MovResponseDto>> getMovements();
}
