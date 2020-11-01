package com.example.anteeoneapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val titleView: TextView = itemView.findViewById(R.id.item_place_title)
    val descriptionView: TextView = itemView.findViewById(R.id.item_place_description)

    fun bind(place: Place){
        titleView.text = place.title
        descriptionView.text = place.description
    }

    fun updateFromBundle(bundle: Bundle){
        if (bundle.containsKey("ARG_TITLE")) {
            bundle.getString("ARG_TITLE").also {
                titleView.text = it
            }
        }
        if (bundle.containsKey("ARG_DESCRIPTION")) {
            bundle.getString("ARG_DESCRIPTION").also {
                descriptionView.text = it
            }
        }
    }

    companion object{
        fun create(parent: ViewGroup) = PlaceHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_place,parent,false)
        )
    }

}