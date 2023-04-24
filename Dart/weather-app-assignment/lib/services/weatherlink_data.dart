
import 'dart:convert';

import 'package:crypto/crypto.dart';
import 'package:http/http.dart' as http;

import '../model/weather_data.dart';

class WeatherlinkData {
  static const _apiKey = 'ohmnrgzurma7y6zubutql6dpy2vka6xk';
  static const _secret = 'rtlwzzja2c3hl5gkueomt0bnebtig45p';

  static const _stationId = '112912';

  static const endPointUrl =
      'https://api.weatherlink.com/v2/current/$_stationId';
  final httpClient = http.Client();

  Future<WeatherData> getWeather() async {
    final requestUrl = '$endPointUrl?api-key=$_apiKey&t=${_unixTime()}&api-signature=${_hash(now: _unixTime())}';

    print(requestUrl);

    final response = await httpClient.get(Uri.parse(requestUrl));

    return WeatherData.fromWeatherlink(jsonDecode(response.body));
  }

  String _unixTime() {
    return "${DateTime.now().millisecondsSinceEpoch ~/ 1000}";
  }

  String _hash({required String now}) {
    final message = 'api-key${_apiKey}station-id${_stationId}t$now';
    final bytes = utf8.encode(message);
    final key = utf8.encode(_secret);
    final hmacSha256 = Hmac(sha256, key);

    return  hmacSha256.convert(bytes).toString();
  }
}
