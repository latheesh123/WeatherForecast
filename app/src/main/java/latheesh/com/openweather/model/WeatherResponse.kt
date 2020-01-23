package latheesh.com.openweather.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("list") val list: Array<List>,
    @SerializedName("city") val city: City
) : Serializable

data class List(
    @SerializedName("temp") val temp: Temp,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: Array<Weather>,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Long,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("speed") val speed: Double
) : Serializable

data class Clouds(
    @SerializedName("temp") val temp: Int
)

data class Weather(
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
) : Serializable

data class Temp(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double
) : Serializable


data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

data class City(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
) : Serializable