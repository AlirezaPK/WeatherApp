package ir.kodato.weatherapp.data.remote.dto.weather

data class WeatherDto(
    val current: CurrentDto,
    val forecast: ForecastDto,
    val location: LocationDto
)