package ir.kodato.weatherapp.data.repository

import ir.kodato.weatherapp.data.mapper.toCityItem
import ir.kodato.weatherapp.data.remote.WeatherApi
import ir.kodato.weatherapp.domain.model.city.City
import ir.kodato.weatherapp.domain.repository.CityDataStore
import ir.kodato.weatherapp.domain.repository.CityRepository
import ir.kodato.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val cityDataStore: CityDataStore
) : CityRepository {

    override suspend fun getCity(query: String): Resource<List<City>> {
        return try {
            val searchedCity = api.searchCity(query = query)
            val cities: MutableList<City> = mutableListOf()
            searchedCity.forEach { city ->
                cities.add(city.toCityItem())
            }
            Resource.Success(data = cities)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Unknown Error Occurred!")
        }
    }

    override suspend fun saveCity(cityName: String) {
        cityDataStore.saveCity(cityName)
    }

    override fun readCity(): Flow<String> {
        return cityDataStore.readCity()
    }
}