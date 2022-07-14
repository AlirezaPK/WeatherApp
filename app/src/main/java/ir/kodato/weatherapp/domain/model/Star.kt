package ir.kodato.weatherapp.domain.model

import kotlin.math.sin

data class Star(
    var x: Float,
    var y: Float,
    var alpha: Float,
) {
    private val initialAlpha = alpha

    fun update(value: Float) {
        val x = (value - initialAlpha).toDouble()
        val newAlpha = 0.5f + (0.5f * sin(x).toFloat())
        alpha = newAlpha
    }
}