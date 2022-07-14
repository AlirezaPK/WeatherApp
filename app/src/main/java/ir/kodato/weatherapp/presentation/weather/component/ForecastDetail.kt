package ir.kodato.weatherapp.presentation.weather.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.kodato.weatherapp.domain.model.weather.ForecastDay
import ir.kodato.weatherapp.ui.theme.Black

@Composable
fun ForecastDetail(
    forecastDays: List<ForecastDay>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Today",
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Black,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        LazyRow(contentPadding = PaddingValues(8.dp)) {
            items(forecastDays) { data ->
                data.hour.forEach { hour ->
                    WeatherItem(
                        icon = data.day.condition.icon,
                        date = hour.time,
                        degree = data.day.averageTemperature.toString()
                    )
                }
            }
        }

        Text(
            text = "Forecast",
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Black,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        LazyRow(contentPadding = PaddingValues(8.dp)) {
            items(forecastDays) { data ->
                WeatherItem(
                    icon = data.day.condition.icon,
                    date = data.date,
                    degree = data.day.averageTemperature.toString()
                )
            }
        }
    }
}