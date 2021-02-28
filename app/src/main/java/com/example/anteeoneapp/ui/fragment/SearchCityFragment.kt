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
import com.example.anteeoneapp.data.converters.weatherListConverter
import com.example.anteeoneapp.domain.network.ApiFactory
import com.example.anteeoneapp.ui.fragment.rv.WeatherListAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.lang.Exception


class SearchCityFragment : Fragment() {

    private val api = ApiFactory.weatherApi
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var mRecyclerView: RecyclerView? = null
    private var mSearchView: SearchView? = null

    private final val STANDART_LATITUDE = 54.550546
    private final val STANDART_LONGITUDE = 53.602365

    private val appInstance = App.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.also {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
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
        getLastKnownLocation()
        initSearch()

    }

    private fun initMembers() {
        mRecyclerView = view?.findViewById(R.id.rv_weather_list)
        mSearchView = view?.findViewById(R.id.searchView)

    }

    private fun getLastKnownLocation() {
        Coordinates(0.0, 0.0)
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
            initRecycler(Coordinates(STANDART_LATITUDE, STANDART_LONGITUDE))
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Log.println(Log.DEBUG, "weather-tag", "location is works")
                    initRecycler(Coordinates(location.latitude, location.longitude))
                } else {
                    Toast.makeText(this.context, "Location is unavailable", Toast.LENGTH_SHORT)
                        .show()
                    initRecycler(Coordinates(STANDART_LATITUDE, STANDART_LONGITUDE))
                }

            }
    }

    private fun initRecycler(coordinates: Coordinates) {
        mRecyclerView?.run {
            lifecycleScope.launch {
                try {
                    val weatherListModel =
                        api.getWeatherList(coordinates.latitude, coordinates.longitude, 50)
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
                        val queryWeather = api.getWeather(query)

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
                Log.println(Log.DEBUG, "search-tag", "Query was changed")
                return false
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchCityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchCityFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    data class Coordinates(val latitude: Double, val longitude: Double)


}