package ir.kodato.weatherapp.domain.model.weather

data class Location(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String
)
