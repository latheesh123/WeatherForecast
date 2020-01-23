package latheesh.com.openweather.service

import latheesh.com.openweather.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/forecast")
    fun getForecastDetails(@Query("q") q: String, @Query("mode") mode: String, @Query("appid") appId: String):Call<WeatherResponse>

}