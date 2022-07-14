package ir.kodato.weatherapp.presentation.weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.kodato.weatherapp.R
import ir.kodato.weatherapp.domain.model.weather.Current
import ir.kodato.weatherapp.domain.model.weather.Location
import ir.kodato.weatherapp.ui.theme.Black
import ir.kodato.weatherapp.ui.theme.White

@Composable
fun TodayWeather(
    currentWeatherState: Current,
    locationState: Location,
    locationClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black.copy(alpha = 0.25f))
            .padding(bottom = 150.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${currentWeatherState.temperature}°",
            fontSize = MaterialTheme.typography.h2.fontSize,
            fontWeight = FontWeight.ExtraLight,
            maxLines = 1,
            color = White
        )

        Text(
            text = currentWeatherState.condition.text,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.ExtraLight,
            maxLines = 1,
            color = White.copy(alpha = ContentAlpha.high)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                locationClicked()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = stringResource(R.string.ic_location),
                tint = White.copy(alpha = ContentAlpha.high)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = locationState.name,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                color = White.copy(alpha = ContentAlpha.high)
            )
        }
    }
}