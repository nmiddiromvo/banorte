// ignore_for_file: file_names

import 'package:http/http.dart' as http;

class ApiConection {
  //Participante: Luis Angel Vazquez Diaz

  Future isFavorite (int idUsuario) async {
    try {
      final response = await http.post(
        Uri.parse('http://localhost:8080/api/movimientos/$idUsuario/favorito'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
      );

      if (response.statusCode == 200) {
        print('Exito');
      } else if (response.statusCode == 401) {
        throw "Se actualizo el registro!";
      } else {
        throw "Ocurrio un error al intentar guardar como favorito";
      }
    } catch (e) {
      throw e.toString();
    }
  }
}