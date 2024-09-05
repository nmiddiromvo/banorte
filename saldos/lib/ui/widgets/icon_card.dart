import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:saldos/ui/theme/app_colors.dart';

class IconCard extends StatefulWidget {
  final String srcImage;
  final String text;

  const IconCard({super.key, this.srcImage = "", this.text = ""});

  @override
  State<IconCard> createState() => _IconCardState();
}

class _IconCardState extends State<IconCard> {
  @override
  Widget build(BuildContext context) {
    return Card(
      shadowColor: AppColors.strokeStart,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(8.0),
      ),
      //elevation: 3,
      child: Container(
        width: 103,
        height: 90,
        padding: const EdgeInsets.symmetric(horizontal: 18, vertical: 10),
        decoration: BoxDecoration(
          gradient: const LinearGradient(
              colors: [AppColors.bStart, AppColors.bEnd],
              begin: Alignment.topLeft),
          borderRadius: BorderRadius.circular(8),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            SvgPicture.asset(
              widget.srcImage,
              width: 24,
              height: 24,
            ),
            Text(
              widget.text,
              textAlign: TextAlign.center,
              style: const TextStyle(fontSize: 13, color: AppColors.whiteDeg),
            ),
          ],
        ),
      ),
    );
  }
}
