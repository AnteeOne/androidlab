package com.example.anteeoneapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(val list:List<PhotosCard>) : RecyclerView.Adapter<CardHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        return CardHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(list[position])
    }
}


