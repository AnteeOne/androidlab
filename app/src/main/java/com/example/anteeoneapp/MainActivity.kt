package com.example.anteeoneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    onClickHome()
                    true
                }
                R.id.action_places -> {
                    onClickPlaces()
                    true
                }
                R.id.action_photos -> {
                    onClickPhoto()
                    true
                }
                else -> false
            }

        }

    }

    fun onClickHome(){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,HomeFragment()).commit()
    }

    fun onClickPhoto(){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,PhotosFragment()).commit()
    }

    fun onClickPlaces(){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,PlacesFragment()).commit()
    }
}