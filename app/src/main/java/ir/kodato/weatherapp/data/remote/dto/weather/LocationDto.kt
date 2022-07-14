package ir.kodato.weatherapp.data.remote.dto.weather

data class LocationDto(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String
)