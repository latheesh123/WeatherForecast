package latheesh.com.openweather

import android.content.Context
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import latheesh.com.openweather.model.WeatherResponse
import latheesh.com.openweather.service.WeatherRetrofit
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var _weatherResponse = MutableLiveData<WeatherResponse>()
    var weatherResponse: LiveData<WeatherResponse> = _weatherResponse
    val viewModelScope = CoroutineScope(Dispatchers.IO)
    fun getDetails(cityname: String) {
        //api call
        val response = WeatherRetrofit.getWeatherService()
            .getForecastDetails(cityname, "json", "66879c0b542dace282b556d688237117")
        response.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: retrofit2.Call<WeatherResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: retrofit2.Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                _weatherResponse.value = response.body()
            }

        })
    }


}