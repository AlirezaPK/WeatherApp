package ir.kodato.weatherapp.presentation.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kodato.weatherapp.presentation.city.component.CityItem
import ir.kodato.weatherapp.presentation.destinations.CityScreenDestination
import ir.kodato.weatherapp.presentation.destinations.WeatherScreenDestination
import ir.kodato.weatherapp.ui.theme.Black
import ir.kodato.weatherapp.ui.theme.DayStatusBar
import ir.kodato.weatherapp.ui.theme.White

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

    if (changeCity) {
        val systemUiController = rememberSystemUiController()
        LaunchedEffect(key1 = true) {
            systemUiController.setStatusBarColor(Black)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text(text = "Search City") },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    focusedBorderColor = Black,
                    unfocusedBorderColor = Black.copy(alpha = ContentAlpha.disabled)
                ),
                value = state.search,
                onValueChange = {
                    viewModel.onEvent(CityEvent.EnteredCity(it))
                    viewModel.onEvent(CityEvent.GetCity(it))
                })

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(state.city) {
                    CityItem(
                        name = it.name,
                        cityClicked = {
                            viewModel.onEvent(CityEvent.SaveCity(it.name))
                            navigator.navigate(WeatherScreenDestination) {
                                popUpTo(CityScreenDestination.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    } else if (state.isCityExist) {
        navigator.navigate(WeatherScreenDestination) {
            popUpTo(CityScreenDestination.route) {
                inclusive = true
            }
        }
    }
}