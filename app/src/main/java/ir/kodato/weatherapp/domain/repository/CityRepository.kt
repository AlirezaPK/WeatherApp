package ir.kodato.weatherapp.domain.repository

import ir.kodato.weatherapp.domain.model.city.City
import ir.kodato.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun getCity(query: String): Resource<List<City>>

    suspend fun saveCity(cityName: String)
    fun readCity(): Flow<String>
}