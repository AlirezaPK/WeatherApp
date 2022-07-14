package ir.kodato.weatherapp.data.remote.dto.weather

import com.squareup.moshi.Json

data class HourDto(
    val condition: ConditionDto,
    @field:Json(name = "temp_c")
    val temperature: Double,
    val time: String
)