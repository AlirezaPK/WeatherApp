package ir.kodato.weatherapp.domain.model.weather

import java.time.LocalDateTime
import java.time.LocalTime

data class Hour(
    val condition: Condition,
    val temperature: Double,
    val time: String
)
