package ir.kodato.weatherapp.data.mapper

import ir.kodato.weatherapp.data.remote.dto.city.CityDto
import ir.kodato.weatherapp.domain.model.city.City

fun CityDto.toCityItem(): City {
    return City(
        country = country,
        name = name
    )
}