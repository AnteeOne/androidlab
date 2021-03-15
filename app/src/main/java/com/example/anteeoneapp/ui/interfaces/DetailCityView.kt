package com.example.anteeoneapp.ui.interfaces

import com.example.anteeoneapp.data.jsonmodel.WeatherDetailModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailCityView : MvpView {

    fun showWeather(cityWeather: WeatherDetailModel)


}