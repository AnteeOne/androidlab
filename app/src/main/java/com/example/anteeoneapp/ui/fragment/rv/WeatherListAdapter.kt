package com.example.anteeoneapp.ui.fragment.rv

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.R
import com.example.anteeoneapp.data.jsonmodel.WeatherListModel

class WeatherListAdapter(private var weatherListModel: WeatherListModel,
                         private val onClick:(String) -> (Unit)) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherListVH>() {

    class WeatherListVH(itemView: View,private val onClick: (String) -> Unit) :RecyclerView.ViewHolder(itemView){

        private val weatherTown:TextView = itemView.findViewById(R.id.rv_item_weather_card_town)
        private val weatherTemperature:TextView = itemView.findViewById(R.id.rv_item_weather_card_temp)
        private val weatherThermometer: ImageView = itemView.findViewById(R.id.rv_item_weather_card_thermo)
        private val weatherAir: ImageView = itemView.findViewById(R.id.rv_item_weather_card_air)
        private val weatherUmbrella: ImageView = itemView.findViewById(R.id.rv_item_weather_card_umbrella)

        fun bind(weatherDetail: WeatherListModel.WeatherDetail){
            weatherTown.text = weatherDetail.name
            val temperature = weatherDetail.main.temp.toInt()
            weatherTemperature.text = itemView.resources
                .getString(R.string.weather_celsius,temperature.toString())
            colorizeThermometer(temperature,weatherThermometer,itemView)
            colorizeAir(weatherDetail.wind.speed.toInt(),weatherAir,itemView)
            colorizeUmbrella(weatherDetail.rain,weatherUmbrella,itemView)
            itemView.setOnClickListener {

                onClick(weatherDetail.name)
            }

        }

        private fun colorizeThermometer(temperature:Int,weatherThermometer:ImageView,itemView: View){
            when(temperature){
                in 20..100 -> weatherThermometer.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorTemperature_20),
                    PorterDuff.Mode.MULTIPLY)
                in 10..20 -> weatherThermometer.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorTemperature_10),
                    PorterDuff.Mode.MULTIPLY)
                in 0..10 -> weatherThermometer.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorTemperature_0),
                    PorterDuff.Mode.MULTIPLY)
                in -10..0 -> weatherThermometer.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorTemperatureCold_10),
                    PorterDuff.Mode.MULTIPLY)
                in -20..-10 -> weatherThermometer.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorTemperatureCold_20),
                    PorterDuff.Mode.MULTIPLY)
                in -100..-10 -> weatherThermometer.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorTemperatureCold_100),
                    PorterDuff.Mode.MULTIPLY)
            }
        }

        private fun colorizeAir(airSpeed:Int,weatherAir:ImageView,itemView: View){
            when(airSpeed){
                in 15..100 -> weatherAir.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorWeatherDangerous),
                    PorterDuff.Mode.MULTIPLY)
                in 7..15 -> weatherAir.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorWeatherMiddle),
                    PorterDuff.Mode.MULTIPLY)
                in 0..15 -> weatherAir.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorWeatherPeaceful),
                    PorterDuff.Mode.MULTIPLY)
            }

        }

        private fun colorizeUmbrella(rain:Any?,weatherUmbrella:ImageView,itemView: View){
//            if (rain != null )
                weatherUmbrella.setColorFilter(ContextCompat.getColor(itemView.context,R.color.colorAccent),
                    PorterDuff.Mode.MULTIPLY)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListVH {
        return WeatherListVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_weather,parent,false),onClick
        )
    }

    override fun getItemCount() = weatherListModel.list.size

    override fun onBindViewHolder(holder: WeatherListVH, position: Int) {
        holder.bind(weatherListModel.list[position])
    }



}