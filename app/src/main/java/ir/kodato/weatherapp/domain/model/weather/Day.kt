package ir.kodato.weatherapp.domain.model.weather

import ir.kodato.weatherapp.data.remote.dto.weather.ConditionDto

data class Day(
    val averageTemperature: Double,
    val condition: Condition
)
