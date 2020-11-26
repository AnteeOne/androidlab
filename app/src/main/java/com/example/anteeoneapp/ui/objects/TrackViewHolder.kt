package com.example.anteeoneapp.ui.objects

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.R
import com.example.anteeoneapp.models.Track

class TrackViewHolder(itemView:View,
                      private val itemClick: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {

    private val titleView:TextView = itemView.findViewById(R.id.track_list_item_title)
    private val authorView:TextView = itemView.findViewById(R.id.track_list_item_author)
    private val coverView: ImageView = itemView.findViewById(R.id.track_list_item_cover)

    fun bind(track: Track){
        titleView.text = track.title
        authorView.text = track.author
        coverView.setImageResource(track.cover)
        itemView.setOnClickListener{
            Log.println(Log.DEBUG,"debugtag","track_id = ${track.id}")
            itemClick(track.id)
        }
    }


    companion object{

        fun create(parent:ViewGroup,itemClick: (Int) -> Unit) = TrackViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.track_list_item,parent,false),
            itemClick
        )
    }
}