package ir.kodato.weatherapp.presentation.city

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kodato.weatherapp.domain.repository.CityRepository
import ir.kodato.weatherapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {

    var state by mutableStateOf(CityState())
        private set

    init {
        checkSavedCity()
    }

    private fun checkSavedCity() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.readCity().collect { savedCity ->
                state = if (savedCity.isBlank()) {
                    state.copy(isCityExist = false, isLoading = false)
                } else {
                    state.copy(isCityExist = true, isLoading = false)
                }
            }
        }
    }

    fun onEvent(event: CityEvent) {
        when (event) {
            is CityEvent.GetCity -> {
                searchCity(event.query)
            }

            is CityEvent.EnteredCity -> {
                state = state.copy(search = event.city)
            }

            is CityEvent.SaveCity -> {
                viewModelScope.launch {
                    repository.saveCity(event.city)
                }
            }
        }
    }

    private fun searchCity(query: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getCity(query).let { result ->
                delay(500)
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            city = result.data!!,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}