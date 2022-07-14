package ir.kodato.weatherapp.presentation.city.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.kodato.weatherapp.ui.theme.Black

@Composable
fun CityItem(
    name: String,
    cityClicked: () -> Unit
) {
    Column(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            cityClicked()
        }
    ) {
        Text(
            text = name,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Black
        )
        Divider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = Black.copy(alpha = ContentAlpha.disabled)
        )
    }
}