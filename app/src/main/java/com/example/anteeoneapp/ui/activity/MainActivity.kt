package com.example.anteeoneapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anteeoneapp.R
import com.example.anteeoneapp.ui.fragment.SearchCityFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.ma_fragment_container,SearchCityFragment.newInstance("asd","as"))
            .commit()
    }
}