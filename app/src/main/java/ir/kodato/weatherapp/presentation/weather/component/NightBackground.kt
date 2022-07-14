package ir.kodato.weatherapp.presentation.weather.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.kodato.weatherapp.R
import ir.kodato.weatherapp.domain.model.Star
import ir.kodato.weatherapp.ui.theme.DarkPurple
import ir.kodato.weatherapp.ui.theme.LightPurple
import kotlin.math.sin

@Composable
fun NightBackground() {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(DarkPurple, LightPurple)))
        ) {
            Stars()
        }

        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            Image(
                painter = painterResource(id = R.drawable.moon),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.illustration_moon),
                modifier = Modifier.padding(top = 60.dp)
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(id = R.drawable.night_mountains),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.illustration_night_mountains),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun Stars(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val infinitelyAnimatedFloat = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(10000),
            repeatMode = RepeatMode.Restart
        )
    )
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        val width = LocalDensity.current.run { maxWidth.toPx() }
        val height = LocalDensity.current.run { maxHeight.toPx() }
        val stars = remember {
            buildList {
                repeat(1000) {
                    val x = (Math.random() * width).toFloat()
                    val y = (Math.random() * height).toFloat()
                    val alpha = (Math.random() * 2.0 * Math.PI).toFloat()
                    add(Star(x, y, alpha))
                }
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            for (star in stars) {
                star.update(infinitelyAnimatedFloat.value)
                drawCircle(
                    color = Color.White,
                    center = Offset(star.x, star.y),
                    radius = 2f,
                    alpha = star.alpha,
                )
            }
        }
    }
}