package ir.kodato.weatherapp.presentation.city

import ir.kodato.weatherapp.domain.model.city.City

data class CityState(
    val city: List<City> = emptyList(),
    val isCityExist:Boolean = false,
    val search: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
