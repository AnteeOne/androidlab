package com.example.anteeoneapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        id2_button.setOnClickListener{
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("KEY","1")
            })
            finish()
        }
    }
}