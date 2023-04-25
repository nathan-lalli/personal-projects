
import 'package:flutter/material.dart';

import '../model/weather_data.dart';
import '../services/fake_data.dart';
import '../services/weatherlink_data.dart';

class WeatherViewModel extends ChangeNotifier {
  late WeatherData _weatherData;

  bool isLoading = true;

  IconData get icon {
    return Icons.wb_sunny_outlined;
  }

  int get currentTemp {
    return _weatherData.temperature;
  }

  int get feelsLike {
    return _weatherData.feelsLikeTemperature;
  }

  String get windDirection {
    final dir = _weatherData.windDirection;
    if (dir < 11.25) return 'N';
    if (dir < 33.75) return 'NNE';
    if (dir < 56.25) return 'NE';
    if (dir < 78.75) return 'ENE';
    if (dir < 101.25) return 'E';
    if (dir < 123.75) return 'ESE';
    if (dir < 146.25) return 'SE';
    if (dir < 168.75) return 'SSE';
    if (dir < 187.5) return 'S';
    if (dir < 213.75) return 'SSW';
    if (dir < 236.25) return 'SW';
    if (dir < 258.75) return 'WSW';
    if (dir < 277.5) return 'W';
    if (dir < 303.75) return 'WNW';
    if (dir < 326.25) return 'NW';
    if (dir < 348.75) return 'NNW';
    return 'N';
  }

  int get windSpeed {
    return _weatherData.windSpeed;
  }

  int get humidity {
    return _weatherData.humidity.round();
  }

  DateTime get lastUpdated {
    return _weatherData.lastUpdated;
  }

  WeatherViewModel() {
    refresh();
  }

  Future<void> refresh() async {
    isLoading = true;
    notifyListeners();

    final weatherFuture = FakeData().getWeather();
    final timingFuture = Future.delayed(const Duration(milliseconds: 800));
    _weatherData = (await  Future.wait([weatherFuture, timingFuture]))[0];
    _weatherData = await WeatherlinkData().getWeather();
    isLoading = false;
    notifyListeners();
  }
}
