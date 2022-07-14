package ir.kodato.weatherapp.domain.model.weather

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
