package latheesh.com.openweather

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import latheesh.com.openweather.model.WeatherResponse
import latheesh.com.openweather.service.WeatherRetrofit

class MainViewModel : ViewModel() {

    var weatherResponse: WeatherResponse? = null
    val viewModelScope = CoroutineScope(Dispatchers.IO)

    fun getDetails(cityname: String, context: Context) {
        viewModelScope.launch {

            //api call
            val response = WeatherRetrofit.getWeatherService()
                .getForecastDetails(
                    cityname,
                    context.getString(R.string.mode),
                    context.getString(R.string.App_ID)
                )
            val result = response.execute()
            if (result.isSuccessful) {
                Log.e("result", result.body().toString())
                weatherResponse = result.body()
            } else {
                //error handling
                Log.e("error", result.message())
            }

        }
    }


}