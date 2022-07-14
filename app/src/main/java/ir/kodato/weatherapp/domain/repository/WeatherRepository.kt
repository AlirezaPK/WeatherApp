package ir.kodato.weatherapp.domain.repository

import ir.kodato.weatherapp.domain.model.weather.Weather
import ir.kodato.weatherapp.util.Resource

interface WeatherRepository {

    suspend fun getWeather(query: String): Resource<Weather>
}