package ir.kodato.weatherapp.presentation.weather.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.kodato.weatherapp.R
import ir.kodato.weatherapp.ui.theme.DarkBlue
import ir.kodato.weatherapp.ui.theme.LightBlue

enum class CloudPosition {
    Start, Finish
}

@Composable
fun DayBackground() {
    var cloudState by remember { mutableStateOf(CloudPosition.Start) }
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val offsetAnimation: Dp by animateDpAsState(
        if (cloudState == CloudPosition.Start) (-screenWidthDp) else screenWidthDp,
        animationSpec = infiniteRepeatable(
            animation = tween(40000, easing = LinearEasing)
        )
    )

    LaunchedEffect(key1 = true) {
        cloudState = when (cloudState) {
            CloudPosition.Start -> CloudPosition.Finish
            CloudPosition.Finish -> CloudPosition.Start
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(DarkBlue, LightBlue)))
    ) {
        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.illustration_sun),
                modifier = Modifier.padding(top = 60.dp)
            )
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.clouds),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.clouds_illustration),
                modifier = Modifier
                    .padding(top = 50.dp)
                    .absoluteOffset(x = offsetAnimation)
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(id = R.drawable.day_mountains),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.day_mountains_illustration),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}