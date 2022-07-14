package ir.kodato.weatherapp.data.repository

import ir.kodato.weatherapp.data.mapper.toWeather
import ir.kodato.weatherapp.data.remote.WeatherApi
import ir.kodato.weatherapp.domain.model.weather.Weather
import ir.kodato.weatherapp.domain.repository.WeatherRepository
import ir.kodato.weatherapp.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(query: String): Resource<Weather> {
        return try {
            Resource.Success(
                data = api.getWeather(query = query).toWeather()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Unknown Error Occurred!")
        }
    }
}