package ir.kodato.weatherapp.data.remote

import ir.kodato.weatherapp.BuildConfig
import ir.kodato.weatherapp.data.remote.dto.city.CityDto
import ir.kodato.weatherapp.data.remote.dto.weather.WeatherDto
import ir.kodato.weatherapp.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json?days=10&aqi=no&alerts=no")
    suspend fun getWeather(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String
    ): WeatherDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String
    ): List<CityDto>
}
