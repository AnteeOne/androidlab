package com.example.anteeoneapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anteeoneapp.R
import com.example.anteeoneapp.databinding.ActivityMainBinding
import com.example.anteeoneapp.ui.fragments.TrackListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFunc()
    }

    private fun initFunc(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                TrackListFragment()
            ).commit()
    }



}