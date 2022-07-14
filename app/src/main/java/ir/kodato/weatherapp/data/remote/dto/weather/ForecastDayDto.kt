package ir.kodato.weatherapp.data.remote.dto.weather

data class ForecastDayDto(
    val date: String,
    val day: DayDto,
    val hour: List<HourDto>
)