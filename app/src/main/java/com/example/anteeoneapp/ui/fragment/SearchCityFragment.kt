package com.example.anteeoneapp.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.App
import com.example.anteeoneapp.R
import com.example.anteeoneapp.data.Coordinates
import com.example.anteeoneapp.data.converters.weatherListConverter
import com.example.anteeoneapp.domain.repository.ApiRepositoryImpl
import com.example.anteeoneapp.domain.repository.LocationRepositoryImpl
import com.example.anteeoneapp.ui.fragment.rv.WeatherListAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NullPointerException


class SearchCityFragment : Fragment() {



    private var mRecyclerView: RecyclerView? = null
    private var mSearchView: SearchView? = null



    private val appInstance = App.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.also {
            LocationRepositoryImpl.initFusedClient(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_city, container, false)
    }

    override fun onStart() {
        super.onStart()
        initMembers()
        setLastKnownLocation()
        initSearch()

    }

    private fun initMembers() {
        mRecyclerView = view?.findViewById(R.id.rv_weather_list)
        mSearchView = view?.findViewById(R.id.searchView)

    }

    private fun setLastKnownLocation() {
        Log.println(Log.DEBUG, "weather-tag", "starting getting location...")
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            initRecycler(LocationRepositoryImpl.getDefaultLocation())
        }

        context?.also {
            lifecycleScope.launch {
                try {
                    initRecycler(LocationRepositoryImpl.getLocation(it))
                }
                catch (ex:NullPointerException){
                    initRecycler(LocationRepositoryImpl.getDefaultLocation())
                }

            }

        }
    }

    private fun initRecycler(coordinates: Coordinates) {
        mRecyclerView?.run {
            lifecycleScope.launch {
                try {
                    val weatherListModel =
                    ApiRepositoryImpl.getWeatherList(coordinates)
                    App.getInstance().weatherListDao.apply {
                        deleteAll()
                        insert(weatherListConverter.convertToDtoList(weatherListModel))
                    }
                    adapter = WeatherListAdapter(
                        weatherListModel
                    ) {}
                } catch (e: Exception) {
                    try {
                        adapter = WeatherListAdapter(
                            weatherListConverter.convertToWeatherListModel(App.getInstance().weatherListDao.getAll())
                        ) {
                            Toast.makeText(
                                context,
                                "Internet connection isn't available!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (ex: Exception) {
                        Toast.makeText(
                            context,
                            "Couldn't to connect to local database.Try later!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Toast.makeText(
                        context,
                        "Couldn't to connect to internet.Try later!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            layoutManager = GridLayoutManager(this@SearchCityFragment.context, 2)

        }
    }

    private fun initSearch() {
        mSearchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                lifecycleScope.launch {
                    try {
                        val queryWeather = ApiRepositoryImpl.getWeather(query)

                        fragmentManager?.beginTransaction()?.replace(
                            R.id.ma_fragment_container,
                            CityDetailFragment.newInstance(query)
                        )
                            ?.addToBackStack("list")
                            ?.commit()

                    } catch (ex: Exception) {
                        if (appInstance.weatherDetailDao.isExist(query)){
                            fragmentManager?.beginTransaction()?.replace(
                                R.id.ma_fragment_container,
                                CityDetailFragment.newInstance(query)
                            )
                                ?.addToBackStack("list")
                                ?.commit()
                        }
                        else{
                            Toast.makeText(
                                context,
                                "Couldn't to find the city in database and internet :c",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchCityFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}