package ir.kodato.weatherapp.presentation.city

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kodato.weatherapp.presentation.city.component.CityContent
import ir.kodato.weatherapp.presentation.destinations.CityScreenDestination
import ir.kodato.weatherapp.presentation.destinations.WeatherScreenDestination
import ir.kodato.weatherapp.ui.theme.Black

@ExperimentalTextApi
@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun CityScreen(
    navigator: DestinationsNavigator,
    changeCity: Boolean = false,
    viewModel: CityViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true) {
        systemUiController.setStatusBarColor(Black)
    }

    if (state.isCityExist) {
        if (changeCity) {
            CityContent(
                viewModel = viewModel,
                state = state,
                navigator = navigator
            )
        } else {
            navigator.navigate(WeatherScreenDestination) {
                popUpTo(CityScreenDestination.route) {
                    inclusive = true
                }
            }
        }
    } else {
        CityContent(
            viewModel = viewModel,
            state = state,
            navigator = navigator
        )
    }
}