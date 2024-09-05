import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:saldos/account/data/models/mov_response_dto.dart';
import 'package:saldos/account/presenter/providers/account_repository_provider.dart';

final accountProvider =
    StateNotifierProvider<AccountNotifier, List<MovResponseDto>>((ref) {
  final fetchGetMovs = ref.watch(accountRepositoryProvider).getMovements;

  return AccountNotifier(fetchGetMovs: fetchGetMovs);
});

typedef AccountCallback = Future<List<MovResponseDto>> Function();

class AccountNotifier extends StateNotifier<List<MovResponseDto>> {
  AccountCallback fetchGetMovs;

  AccountNotifier({required this.fetchGetMovs}) : super([]);

  Future<void> loadMovs() async {
    state = await fetchGetMovs();
  }
}
