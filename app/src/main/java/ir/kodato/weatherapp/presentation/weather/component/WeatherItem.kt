package ir.kodato.weatherapp.presentation.weather.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.kodato.weatherapp.R
import ir.kodato.weatherapp.ui.theme.Black
import ir.kodato.weatherapp.ui.theme.Grey
import ir.kodato.weatherapp.ui.theme.White

@Composable
fun WeatherItem(
    icon: String,
    date: String,
    degree: String
) {
    val context = LocalContext.current

    Card(
        backgroundColor = Grey,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_landscape),
                contentDescription = stringResource(R.string.ic_weather_condition),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(70.dp)
            )
            Text(
                text = date,
                fontSize = MaterialTheme.typography.body2.fontSize,
                color = Black
            )
            Text(
                text = "$degree°",
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )
        }
    }
}

@Preview
@Composable
fun WeatherItemPreview() {
    WeatherItem(icon = "", date = "23:00", degree = "123")
}