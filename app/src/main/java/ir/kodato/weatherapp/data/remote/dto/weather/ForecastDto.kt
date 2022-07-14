package ir.kodato.weatherapp.data.remote.dto.weather

import com.squareup.moshi.Json

data class ForecastDto(
    @field:Json(name = "forecastday")
    val days: List<ForecastDayDto>
)