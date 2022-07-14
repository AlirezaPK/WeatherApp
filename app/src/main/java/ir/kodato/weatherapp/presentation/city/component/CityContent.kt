package ir.kodato.weatherapp.presentation.city.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kodato.weatherapp.presentation.city.CityEvent
import ir.kodato.weatherapp.presentation.city.CityState
import ir.kodato.weatherapp.presentation.city.CityViewModel
import ir.kodato.weatherapp.presentation.destinations.CityScreenDestination
import ir.kodato.weatherapp.presentation.destinations.WeatherScreenDestination
import ir.kodato.weatherapp.ui.theme.Black
import ir.kodato.weatherapp.ui.theme.White

@ExperimentalTextApi
@ExperimentalMaterialApi
@Composable
fun CityContent(
    viewModel: CityViewModel,
    state: CityState,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester),
            placeholder = { Text(text = "Search City") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
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
}