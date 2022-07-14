package ir.kodato.weatherapp.domain.model.weather

import java.time.LocalDate
import java.time.LocalDateTime

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)
