package ir.kodato.weatherapp.presentation.weather

import ir.kodato.weatherapp.domain.model.weather.Weather

data class WeatherState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
