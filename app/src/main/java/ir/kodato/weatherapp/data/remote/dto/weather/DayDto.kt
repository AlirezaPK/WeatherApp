package ir.kodato.weatherapp.data.remote.dto.weather

import com.squareup.moshi.Json

data class DayDto(
    @field:Json(name = "avgtemp_c")
    val averageTemperature: Double,
    val condition: ConditionDto
)