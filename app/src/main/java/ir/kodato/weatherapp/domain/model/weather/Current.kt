package ir.kodato.weatherapp.domain.model.weather

data class Current(
    val condition: Condition,
    val feelsLike: Double,
    val humidity: Int,
    val isDay: Int,
    val temperature: Double,
    val wind: Double
)
