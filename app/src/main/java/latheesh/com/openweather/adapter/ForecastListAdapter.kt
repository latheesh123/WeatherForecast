package latheesh.com.openweather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import latheesh.com.openweather.R
import latheesh.com.openweather.model.WeatherResponse

class ForecastListAdapter(
    private var forecastList:WeatherResponse?
) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return forecastList?.list?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text=forecastList?.list?.get(position)?.weather?.get(0)?.main
        holder.textview2.text=forecastList?.list?.get(position)?.weather?.get(0)?.description

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView:View
        itemView=LayoutInflater.from(parent.context).inflate(R.layout.forecast_view,parent,false)
        return ViewHolder(itemView)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val textView=itemView.findViewById<TextView>(R.id.temp)
        val textview2=itemView.findViewById<TextView>(R.id.climate)
    }


}