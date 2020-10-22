package com.example.anteeoneapp

import UserHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private var usersList:List<User> ): RecyclerView.Adapter<UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent,false)
        return UserHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.position = position
        holder.userName.text = usersList[position].name
        holder.userInfo.text = usersList[position].info
        holder.userAvatar.setImageResource(usersList[position].avatar)
    }

}