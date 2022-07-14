package ir.kodato.weatherapp.presentation.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kodato.weatherapp.domain.repository.CityRepository
import ir.kodato.weatherapp.domain.repository.WeatherRepository
import ir.kodato.weatherapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            cityRepository.readCity().collect { cityName ->
                repository.getWeather(cityName).let { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                weather = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message,
                                weather = null
                            )
                        }
                    }
                }
            }
        }
    }
}