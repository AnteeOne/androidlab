package com.example.anteeoneapp.ui.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.R
import com.example.anteeoneapp.ui.fragment.rv.WeatherListAdapter
import com.example.anteeoneapp.ui.interfaces.SearchCityView
import com.example.anteeoneapp.ui.presenters.SearchCityPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class SearchCityFragment : MvpAppCompatFragment(),SearchCityView {

    private var mRecyclerView: RecyclerView? = null
    private var mSearchView: SearchView? = null
    private var mProgressBar: ProgressBar? = null

    @InjectPresenter
    lateinit var presenter: SearchCityPresenter

    @ProvidePresenter
    fun providePresenter(): SearchCityPresenter? = context?.let { initPresenter(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_city, container, false)
    }

    override fun initMembers() {
        mRecyclerView = view?.findViewById(R.id.rv_weather_list)
        mSearchView = view?.findViewById(R.id.searchView)
        mProgressBar = view?.findViewById(R.id.progressBar)
    }

    override fun initLastKnownLocation() {
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
            presenter.setDefaultLocation()
        }
        context?.let { presenter.setLastKnownLocation(it) }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchCityFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun showLoading() {
        mProgressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mProgressBar?.visibility = View.GONE
    }

    override fun initWeatherList(wListAdapter: WeatherListAdapter) {
        mRecyclerView?.run {
            adapter =  wListAdapter
            layoutManager = GridLayoutManager(this@SearchCityFragment.context, 2)
        }
    }

    override fun initSearch() {
        mSearchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.println(Log.INFO,"anttag","on query = $query")
                presenter.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }

        })
    }

    override fun replaceOnDetail(query: String) {
        fragmentManager?.beginTransaction()?.replace(
            R.id.ma_fragment_container,
            CityDetailFragment.newInstance(query)
        )
            ?.addToBackStack("list")
            ?.commit()
    }

    override fun showToast(text: String, duration: Int) {
        Toast.makeText(context,text,duration)
    }

    private fun initPresenter(context: Context) = SearchCityPresenter(context)


}