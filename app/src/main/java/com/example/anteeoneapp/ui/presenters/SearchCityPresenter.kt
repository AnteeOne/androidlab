package com.example.anteeoneapp.ui.presenters

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.anteeoneapp.App
import com.example.anteeoneapp.R
import com.example.anteeoneapp.data.Coordinates
import com.example.anteeoneapp.data.converters.weatherListConverter
import com.example.anteeoneapp.data.jsonmodel.WeatherListModel
import com.example.anteeoneapp.domain.repository.ApiRepositoryImpl
import com.example.anteeoneapp.domain.repository.LocationRepositoryImpl
import com.example.anteeoneapp.ui.fragment.CityDetailFragment
import com.example.anteeoneapp.ui.fragment.rv.WeatherListAdapter
import com.example.anteeoneapp.ui.interfaces.SearchCityView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import java.lang.Exception
import java.lang.NullPointerException

@InjectViewState
class SearchCityPresenter(var context: Context) : MvpPresenter<SearchCityView>(){

    private val appInstance = App.getInstance()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        context.also {
            LocationRepositoryImpl.initFusedClient(it)
        }
        viewState.initMembers()
        viewState.initLastKnownLocation()
        viewState.initSearch()
    }

    fun setDefaultLocation(){
        initRecyclerView(LocationRepositoryImpl.getDefaultLocation())
    }

    fun setLastKnownLocation(context: Context){
        context.also {
            presenterScope.launch {
                try {
                    initRecyclerView(LocationRepositoryImpl.getLocation(it))
                } catch (ex: NullPointerException){
                    initRecyclerView(LocationRepositoryImpl.getDefaultLocation())
                }
            }

        }
    }

    fun onQueryTextSubmit(query: String){
        presenterScope.launch {
            try {
                val queryWeather = ApiRepositoryImpl.getWeather(query)
                viewState.replaceOnDetail(query)
            } catch (ex: Exception) {
                if (appInstance.weatherDetailDao.isExist(query)){
                    viewState.replaceOnDetail(query)
                }
                else{
                    viewState.showToast("Couldn't to find the city in database and internet :c",Toast.LENGTH_SHORT)
                }
            }

        }
    }

    private fun initRecyclerView(coordinates:Coordinates){
        presenterScope.launch {
            viewState.initWeatherList(getWeatherListAdapter(coordinates))
        }
    }

    private suspend fun getWeatherListAdapter(coordinates: Coordinates):WeatherListAdapter{
        lateinit var result:WeatherListAdapter
        try {
            viewState.showLoading()
            val weatherListModel = ApiRepositoryImpl.getWeatherList(coordinates)
            saveToDatabase(weatherListModel)
            result = WeatherListAdapter(weatherListModel){}
        } catch (e: Exception) {
            try {
                result = WeatherListAdapter(
                    weatherListConverter.convertToWeatherListModel(App.getInstance().weatherListDao.getAll())
                ) {
                    viewState.showToast("Internet connection isn't available!",Toast.LENGTH_SHORT)
                }
            } catch (ex: Exception) {
                viewState.showToast("Couldn't to connect to local database.Try later!",Toast.LENGTH_SHORT)
            }
            viewState.showToast("Couldn't to connect to internet.Try later!",Toast.LENGTH_SHORT)
        }
        finally {
            viewState.hideLoading()
        }
        return result
    }

    private fun saveToDatabase(weatherListModel: WeatherListModel){
        App.getInstance().weatherListDao.apply {
            deleteAll()
            insert(weatherListConverter.convertToDtoList(weatherListModel))
        }
    }
}