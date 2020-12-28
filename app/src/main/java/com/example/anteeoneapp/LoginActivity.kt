package com.example.anteeoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        id_loginButton.setOnClickListener(){
            var intent:Intent = Intent(this,MainActivity::class.java)
            intent.putExtra("NAME",id_editTextLoginName.text.toString())
            intent.putExtra("EDUCATION",id_editTextLoginEducation.text.toString())
            intent.putExtra("HOME",id_editTextLoginHome.text.toString())
            intent.putExtra("RELATIONSHIP",id_editTextLoginRelationship.text.toString())
            intent.putExtra("WORK",id_editTextLoginWork.text.toString())
            startActivity(intent)

        }


    }
}