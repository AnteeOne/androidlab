package com.example.anteeoneapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.user_info.*

class UserDetailActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info)
        val usersList = UsersReposytory.getUsersReposytory()
        val id = intent.getStringExtra("id")?.toInt()
        userDetailItemAvatar.setImageResource(usersList[id!!].avatar)
        userDetailItemName.text = usersList[id].name
        userDetailItemInfo.text = usersList[id].info
    }
}