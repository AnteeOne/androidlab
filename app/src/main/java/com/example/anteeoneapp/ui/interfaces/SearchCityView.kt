package com.example.anteeoneapp.ui.interfaces

import android.widget.Toast
import com.example.anteeoneapp.ui.fragment.rv.WeatherListAdapter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchCityView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun initWeatherList(wListAdapter: WeatherListAdapter)

    fun initSearch()

    fun showToast(text:String,duration:Int)

    @StateStrategyType(SkipStrategy::class)
    fun replaceOnDetail(query:String)

    fun initMembers()

    fun initLastKnownLocation()
}