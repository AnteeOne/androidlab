package com.example.anteeoneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        id_name.setText(getIntent().getStringExtra("NAME"))
        id_home.setText(getIntent().getStringExtra("HOME"))
        id_education.setText(getIntent().getStringExtra("EDUCATION"))
        id_work.setText(getIntent().getStringExtra("WORK"))
        id_relationship.setText(getIntent().getStringExtra("RELATIONSHIP"))
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