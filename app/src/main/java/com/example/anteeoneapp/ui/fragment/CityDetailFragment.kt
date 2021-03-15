package com.example.anteeoneapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anteeoneapp.R
import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel
import com.example.anteeoneapp.ui.interfaces.DetailCityView
import com.example.anteeoneapp.ui.presenters.DetailCityPresenter
import kotlinx.android.synthetic.main.fragment_city_detail.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.text.SimpleDateFormat

private const val ARG_PARAM1 = "CITY_TITLE"
private const val DEF_PARAM1 = "JOJO"

class CityDetailFragment : MvpAppCompatFragment(),DetailCityView {

    @InjectPresenter
    lateinit var presenter: DetailCityPresenter

    @ProvidePresenter
    fun providePresenter(): DetailCityPresenter = initPresenter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_detail, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            CityDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun showWeather(cityWeather: WeatherDetailModel) {
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

    private fun initPresenter() = DetailCityPresenter(arguments?.getString(ARG_PARAM1)?: DEF_PARAM1)
}