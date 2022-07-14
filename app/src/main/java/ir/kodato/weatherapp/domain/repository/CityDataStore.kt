package ir.kodato.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CityDataStore {
    suspend fun saveCity(cityName: String)
    fun readCity(): Flow<String>
}