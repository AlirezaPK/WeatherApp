package ir.kodato.weatherapp.data.mapper

import ir.kodato.weatherapp.data.remote.dto.weather.*
import ir.kodato.weatherapp.domain.model.weather.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ConditionDto.toCondition(): Condition {
    return Condition(
        icon = "https://$icon",
        text = text
    )
}

fun CurrentDto.toCurrent(): Current {
    return Current(
        condition = condition.toCondition(),
        feelsLike = feelsLike,
        humidity = humidity,
        isDay = isDay,
        temperature = temperature,
        wind = wind
    )
}

fun DayDto.toDay(): Day {
    return Day(
        averageTemperature = averageTemperature,
        condition = condition.toCondition()
    )
}

fun ForecastDayDto.toForecastDay(): ForecastDay {
    val hour = hour.filter {
        it.time <= LocalDateTime.now().toString()
    }.map {
        it.toHour()
    }
    return ForecastDay(
        date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(
            DateTimeFormatter.ofPattern("MM/dd")
        ),
        day = day.toDay(),
        hour = hour
    )
}

fun ForecastDto.toForecast(): Forecast {
    return Forecast(
        forecastDays = days.map { it.toForecastDay() }
    )
}

fun HourDto.toHour(): Hour {
    val time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        .format(DateTimeFormatter.ofPattern("HH:mm"))

    return Hour(
        condition = condition.toCondition(),
        temperature = temperature,
        time = time
    )
}

fun LocationDto.toLocation(): Location {
    return Location(
        country = country,
        localtime = localtime,
        name = name,
        region = region
    )
}

fun WeatherDto.toWeather(): Weather {
    return Weather(
        current = current.toCurrent(),
        forecast = forecast.toForecast(),
        location = location.toLocation()
    )
}