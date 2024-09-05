import 'package:saldos/account/data/datasource/account_datasource.dart';
import 'package:saldos/account/data/models/mov_response_dto.dart';
import 'package:saldos/account/data/repository/account_repository.dart';

class AccountRepositoryImpl implements AccountRepository {
  final AccountDatasource api;

  AccountRepositoryImpl(this.api);

  @override
  Future<List<MovResponseDto>> getMovements() {
    return api.getMovements();
  }
}
