package com.example.anteeoneapp


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        id_bottomNav_1.setOnClickListener {
            supportFragmentManager.beginTransaction().
                    replace(R.id.id_frameLayout,Fragment1.newInstance("1","1")).commit()
            configButtonColors()
            id_bottomNav_1.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent))

        }
        id_bottomNav_2.setOnClickListener {
            supportFragmentManager.beginTransaction().
                    replace(R.id.id_frameLayout,Fragment2.newInstance("1","1")).commit()
            configButtonColors()
            id_bottomNav_2.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent))
        }
        id_bottomNav_3.setOnClickListener {
            supportFragmentManager.beginTransaction().
                    replace(R.id.id_frameLayout,Fragment3.newInstance("1","1")).commit()
            configButtonColors()
            id_bottomNav_3.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent))
        }
        id_bottomNav_4.setOnClickListener {
            supportFragmentManager.beginTransaction().
                    replace(R.id.id_frameLayout,Fragment4.newInstance("1","1")).commit()
            configButtonColors()
            id_bottomNav_4.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent))
        }
        id_bottomNav_5.setOnClickListener {
            supportFragmentManager.beginTransaction().
                    replace(R.id.id_frameLayout,Fragment5.newInstance("1","1")).commit()
            configButtonColors()
            id_bottomNav_5.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent))
        }

    }


    fun configButtonColors(){
        id_bottomNav_1.setColorFilter(ContextCompat.getColor(this,R.color.black))
        id_bottomNav_2.setColorFilter(ContextCompat.getColor(this,R.color.black))
        id_bottomNav_3.setColorFilter(ContextCompat.getColor(this,R.color.black))
        id_bottomNav_4.setColorFilter(ContextCompat.getColor(this,R.color.black))
        id_bottomNav_5.setColorFilter(ContextCompat.getColor(this,R.color.black))
    }
}

