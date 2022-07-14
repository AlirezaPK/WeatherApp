package ir.kodato.weatherapp.presentation.city

sealed class CityEvent {
    data class GetCity(val query: String) : CityEvent()
    data class EnteredCity(val city: String) : CityEvent()
    data class SaveCity(val city: String) : CityEvent()
}
