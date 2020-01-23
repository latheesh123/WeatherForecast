package latheesh.com.openweather

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import latheesh.com.openweather.model.WeatherResponse
import latheesh.com.openweather.service.WeatherRetrofit
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var _dialog = MutableLiveData<Any>()
    var dialog: LiveData<Any> = _dialog
    var _weatherResponse = MutableLiveData<WeatherResponse>()
    var weatherResponse: LiveData<WeatherResponse> = _weatherResponse

    fun getDetails(cityname: String, context: Context) {

        //api call
        val response = WeatherRetrofit.getWeatherService()
            .getForecastDetails(cityname, "json", "66879c0b542dace282b556d688237117")
        response.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: retrofit2.Call<WeatherResponse>, t: Throwable) {
                setMessageForDialog("message")
            }

            override fun onResponse(
                call: retrofit2.Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                _weatherResponse.value = response.body()
            }

        })
    }

    fun setMessageForDialog(message: Any?) {
        _dialog.value = message
    }
}