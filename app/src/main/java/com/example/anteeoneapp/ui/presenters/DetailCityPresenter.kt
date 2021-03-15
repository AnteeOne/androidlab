package com.example.anteeoneapp.ui.presenters

import android.util.Log
import com.example.anteeoneapp.App
import com.example.anteeoneapp.data.converters.weatherDetailConverter
import com.example.anteeoneapp.domain.repository.ApiRepositoryImpl
import com.example.anteeoneapp.ui.interfaces.DetailCityView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class DetailCityPresenter(var cityTitle: String) : MvpPresenter<DetailCityView>(){

    override fun onFirstViewAttach() {
        Log.println(Log.INFO,"anttag",cityTitle)
        super.onFirstViewAttach()
        loadWeather(cityTitle)
    }

    private fun loadWeather(cityTitle: String) {
        presenterScope.launch {
            try{
                val weatherDetailModel = ApiRepositoryImpl.getWeather(cityTitle)
                Log.println(Log.INFO,"anttag",weatherDetailModel.toString())
                App.getInstance().weatherDetailDao.add(weatherDetailConverter.convertToDto(weatherDetailModel))
                viewState.showWeather(weatherDetailModel)
            }
            catch (e:Exception){
                viewState.showWeather(weatherDetailConverter.convertToModel(App.getInstance().weatherDetailDao.getByName(cityTitle)))
            }
        }
    }
}