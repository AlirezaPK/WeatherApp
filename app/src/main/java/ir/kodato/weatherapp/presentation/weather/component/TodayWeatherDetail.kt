package ir.kodato.weatherapp.presentation.weather.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.kodato.weatherapp.R
import ir.kodato.weatherapp.ui.theme.White

enum class ArrowPosition {
    Start, Finish
}

@Composable
fun TodayDetail(
    windSpeed: String,
    humidity: String,
    feelsLike: String
) {
    var arrowState by remember { mutableStateOf(ArrowPosition.Start) }
    val offsetAnimation: Dp by animateDpAsState(
        if (arrowState == ArrowPosition.Start) (-5).dp else 0.dp,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing)
        )
    )
    LaunchedEffect(key1 = true) {
        arrowState = when (arrowState) {
            ArrowPosition.Start -> ArrowPosition.Finish
            ArrowPosition.Finish -> ArrowPosition.Start
        }
    }

    Column(
        modifier = Modifier.background(Color.Transparent)
    ) {
        Card(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            border = BorderStroke(1.dp, White),
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(4.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DetailItem(
                    modifier = Modifier.weight(1f),
                    amount = "$windSpeed km/h",
                    title = "Wind Speed"
                )
                Divider(
                    color = White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 16.dp)
                )
                DetailItem(
                    modifier = Modifier.weight(1f),
                    amount = "$humidity%",
                    title = "Humidity"
                )
                Divider(
                    color = White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 16.dp)
                )
                DetailItem(
                    modifier = Modifier.weight(1f),
                    amount = "$feelsLike°",
                    title = "Feels Like"
                )
            }
        }

        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .absoluteOffset(y = offsetAnimation),
            imageVector = Icons.Rounded.KeyboardArrowUp,
            contentDescription = stringResource(R.string.ic_arrow_up),
            tint = White
        )
    }
}

@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    amount: String,
    title: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = amount,
            fontSize = MaterialTheme.typography.body1.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = MaterialTheme.typography.button.fontSize,
            color = White.copy(alpha = ContentAlpha.high)
        )
    }
}

@Preview
@Composable
fun TodayDetailPreview() {
    TodayDetail("144", "50%", "21")
}