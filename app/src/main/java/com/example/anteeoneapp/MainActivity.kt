package com.example.anteeoneapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        id_intentlaunch.setOnClickListener(){

            val intent = Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            intent.putExtra(Intent.EXTRA_TEXT,"Hey , Jotaro!")
            if(intent.resolveActivity(packageManager)!=null){
                startActivityForResult(intent,1)

            }


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Toast.makeText(this,"User is pressed on button",Toast.LENGTH_SHORT).show()
        }


    }
}