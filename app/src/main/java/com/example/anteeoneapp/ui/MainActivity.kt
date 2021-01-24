package com.example.anteeoneapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anteeoneapp.R
import com.example.anteeoneapp.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container,MainFragment())
            .addToBackStack(null)
            .commit()
    }
}