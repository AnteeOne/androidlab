package com.example.anteeoneapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.anteeoneapp.App
import com.example.anteeoneapp.R
import com.example.anteeoneapp.data.converters.weatherDetailConverter
import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel
import com.example.anteeoneapp.domain.network.ApiFactory
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.fragment_city_detail.*;
import java.text.SimpleDateFormat

private const val ARG_PARAM1 = "CITY_TITLE"

class CityDetailFragment : Fragment() {

    private var city: String? = null
    private val api = ApiFactory.weatherApi
    private val appInstance = App.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_PARAM1)
        }
        city?.let {
            initWeather(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_detail, container, false)
    }

    private fun initWeather(cityTitle: String) {
        lifecycleScope.launch {
            try{
                val weatherDetailModel = api.getWeather(cityTitle)
                App.getInstance().weatherDetailDao.add(weatherDetailConverter.convertToDto(weatherDetailModel))
                initWeatherView(weatherDetailModel)
            }
            catch (e:Exception){
                initWeatherView(weatherDetailConverter.convertToModel(App.getInstance().weatherDetailDao.getByName(cityTitle)))
            }
        }
    }

    private suspend fun initWeatherView(cityWeather: WeatherDetailModel) {
        address.text = cityWeather.name
        temp.text = context?.resources?.getString(
            R.string.weather_celsius,
            cityWeather.main.temp.toInt().toString()
        )
        temp_min.text = context?.resources?.getString(
            R.string.weather_celsius_min,
            cityWeather.main.temp.toInt().toString()
        )
        temp_max.text = context?.resources?.getString(
            R.string.weather_celsius_max,
            cityWeather.main.temp.toInt().toString()
        )
        sunrise.text = SimpleDateFormat("HH:mm").format(cityWeather.sys.sunrise * 1000)
        sunset.text = SimpleDateFormat("HH:mm").format(cityWeather.sys.sunset * 1000)
        wind.text = context?.resources?.getString(R.string.weather_speed,cityWeather.wind.speed.toString())
        pressure.text = cityWeather.main.pressure.toString()
        humidity.text = cityWeather.main.humidity.toString() + "%"
        direction.text = when (cityWeather.wind.deg) {
                in 0..22 -> "N"
                in 23..67 -> "N-E"
                in 68..112 -> "E"
                in 113..157 -> "S-E"
                in 158..202 -> "S"
                in 203..247 -> "S-W"
                in 248..292 -> "S"
                in 293..337 -> "N-W"
                in 337..361 -> "N"
                else -> ":D"
            }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CityDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            CityDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}