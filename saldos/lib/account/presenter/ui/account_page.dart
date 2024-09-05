import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:saldos/account/data/models/mov_response_dto.dart';
import 'package:saldos/account/presenter/providers/account_provider.dart';
import 'package:saldos/account/presenter/providers/account_repository_provider.dart';
import 'package:saldos/ui/theme/app_colors.dart';
import 'package:saldos/ui/widgets/icon_card.dart';

class AccountPage extends StatelessWidget {
  const AccountPage({super.key});

  @override
  Widget build(BuildContext context) {
    return const _AccountView();
  }
}

class _AccountView extends ConsumerStatefulWidget {
  const _AccountView();

  @override
  _AccountViewState createState() => _AccountViewState();
}

class _AccountViewState extends ConsumerState<_AccountView> {
  List<MovResponseDto> movs = [];
  bool isFilter = false;
  String searchingText = "";

  @override
  void initState() {
    // Método que se ejecuta al iniciar el widget
    super.initState();
    ref.read(accountProvider.notifier).fetchGetMovs();
  }

  @override
  void dispose() {
    // Método que se ejecuta al destruir el widget
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // Método que cosntruye la UI

    movs = ref.watch(accountProvider);
    print(movs);
    return Scaffold(
      appBar: AppBar(
        backgroundColor: AppColors.black,
        elevation: 14,
        leading: Padding(
            padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 5),
            child: SvgPicture.asset(
              'assets/back.svg',
              width: 24,
              height: 24,
            )),
        actions: [
          Padding(
            padding: EdgeInsets.only(right: 24),
            child: SvgPicture.asset(
              'assets/hide-balance.svg',
              width: 24,
              height: 24,
            ),
          ),
        ],
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: EdgeInsets.only(left: 24),
            child: Text(
              "Cuenta de ahorro",
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.w400,
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.only(left: 24),
            child: Text(
              "Saldo disponible en **** 5764",
              style: TextStyle(
                fontSize: 12,
                fontWeight: FontWeight.w400,
                color: AppColors.whiteDeg,
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.only(left: 24, bottom: 32),
            child: Row(
              children: [
                Text(
                  '\$ 32,890',
                  style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.w600,
                  ),
                ),
                Text(
                  ".00",
                  textAlign: TextAlign.start,
                  style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.w400,
                      color: AppColors.whiteDeg),
                )
              ],
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              IconCard(
                srcImage: "assets/statement.svg",
                text: "Estados\nde cuenta",
              ),
              IconCard(
                srcImage: "assets/show-details.svg",
                text: "Detalles",
              ),
              IconCard(
                srcImage: "assets/settings.svg",
                text: "Configurar",
              ),
            ],
          ),
          Container(
            padding: EdgeInsets.only(left: 24, right: 24, top: 32),
            child: Column(
              children: [
                SizedBox(
                  height: 56,
                  width: 320,
                  child: SearchAnchor(builder:
                      (BuildContext context, SearchController controller) {
                    return SearchBar(
                      controller: controller,
                      padding: const MaterialStatePropertyAll<EdgeInsets>(
                          EdgeInsets.symmetric(horizontal: 16.0)),
                      onTap: () {
                        controller.openView();
                      },
                      onChanged: (_) {
                        controller.openView();
                      },
                      leading: const Icon(Icons.search),
                      trailing: <Widget>[
                        Tooltip(
                          message: 'Change brightness mode',
                          child: IconButton(
                            isSelected: isFilter,
                            onPressed: () {
                              setState(() {
                                isFilter = !isFilter;
                              });
                            },
                            icon: const Icon(Icons.filter),
                            selectedIcon:
                                const Icon(Icons.brightness_2_outlined),
                          ),
                        )
                      ],
                    );
                  }, suggestionsBuilder:
                      (BuildContext context, SearchController controller) {
                    return List<ListTile>.generate(5, (int index) {
                      final String item = 'item $index';
                      return ListTile(
                        title: Text(item),
                        onTap: () {
                          setState(() {
                            controller.closeView(item);
                          });
                        },
                      );
                    });
                  }),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
