package ir.kodato.weatherapp.data.remote.dto.weather

import com.squareup.moshi.Json

data class CurrentDto(
    val condition: ConditionDto,
    @field:Json(name = "feelslike_c")
    val feelsLike: Double,
    val humidity: Int,
    @field:Json(name = "is_day")
    val isDay: Int,
    @field:Json(name = "temp_c")
    val temperature: Double,
    @field:Json(name = "wind_kph")
    val wind: Double
)