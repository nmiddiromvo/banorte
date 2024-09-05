import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:saldos/account/data/datasource/account_datasource.dart';
import 'package:saldos/account/data/repository/account_repository.dart';
import 'package:saldos/account/datasource/account_datasource_impl.dart';

import '../../data/repository/account_repositoryImpl.dart';

final accountRepositoryProvider = Provider((ref) {
  return AccountRepositoryImpl(AccountDatasourceImpl());
});
