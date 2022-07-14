package ir.kodato.weatherapp.presentation.weather

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kodato.weatherapp.R
import ir.kodato.weatherapp.presentation.destinations.CityScreenDestination
import ir.kodato.weatherapp.presentation.weather.component.*
import ir.kodato.weatherapp.ui.theme.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalTextApi
@Destination
@Composable
fun WeatherScreen(
    navigator: DestinationsNavigator,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                scope.launch {
                    if (dragAmount.y < 0) {
                        sheetState.expand()
                    }
                    if (dragAmount.y > 0) {
                        sheetState.collapse()
                    }
                }
            }
        },
        sheetShape = RoundedCornerShape(16.dp),
        scaffoldState = scaffoldState,
        sheetBackgroundColor = White,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            var state = viewModel.state
            LaunchedEffect(key1 = viewModel.state) {
                state = viewModel.state
            }
            if (!state.isLoading && state.weather != null) {
                ForecastDetail(state.weather!!.forecast.forecastDays)
            }
        }) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (viewModel.state.isLoading || viewModel.state.weather == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            } else {
                val state = viewModel.state.weather!!
                val currentWeatherState = state.current
                val locationState = state.location

                val systemUiController = rememberSystemUiController()

                if (currentWeatherState.isDay == 1) {
                    LaunchedEffect(key1 = true) {
                        systemUiController.setStatusBarColor(DayStatusBar)
                    }
                    DayBackground()
                } else {
                    LaunchedEffect(key1 = true) {
                        systemUiController.setStatusBarColor(NightStatusBar)
                    }
                    NightBackground()
                }

                TodayWeather(
                    currentWeatherState = currentWeatherState,
                    locationState = locationState,
                    locationClicked = {
                        navigator.navigate(CityScreenDestination(changeCity = true))
                    }
                )

                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    TodayDetail(
                        windSpeed = currentWeatherState.wind.toString(),
                        humidity = currentWeatherState.humidity.toString(),
                        feelsLike = currentWeatherState.feelsLike.toString()
                    )
                }
            }
        }
    }
}