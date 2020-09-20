package com.example.anteeoneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        id_editNameButton.setOnClickListener {

            if(id_name.visibility == View.VISIBLE){
                id_name.setVisibility(View.INVISIBLE)
                id_editNameText.setVisibility(View.VISIBLE)
                id_editNameText.setText(id_name.text)
            }
            else{
                id_name.setVisibility(View.VISIBLE)
                id_editNameText.setVisibility(View.INVISIBLE)
                id_name.text = id_editNameText.text
            }

        }
        
    }
}