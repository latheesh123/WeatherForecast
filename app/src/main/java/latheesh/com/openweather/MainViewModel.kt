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
import retrofit2.Response

class MainViewModel : ViewModel() {

    var _weatherResponse= MutableLiveData<WeatherResponse>()
    var weatherResponse: LiveData<WeatherResponse> = _weatherResponse
    val viewModelScope = CoroutineScope(Dispatchers.IO)
    var result: Response<WeatherResponse>? = null
    fun getDetails(cityname: String, context: Context):WeatherResponse? {
        viewModelScope.launch {

            //api call
            val response = WeatherRetrofit.getWeatherService()
                .getForecastDetails(cityname, "json", "66879c0b542dace282b556d688237117")
             result = response.execute()
            if ((result as Response<WeatherResponse>?)?.isSuccessful!!) {
                Log.e("result", (result as Response<WeatherResponse>?)?.body().toString())
            } else {
                //error handling
                Log.e("error", (result as Response<WeatherResponse>?)?.message())
            }

        }

        return result?.body()
    }


}