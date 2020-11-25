package com.example.anteeoneapp.ui.objects

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.models.Track

class TrackListAdapter(private val trackList: ArrayList<Track>,
                       private val itemClick: (Int) -> (Unit)) : RecyclerView.Adapter<TrackViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder.create(parent,itemClick)
    }

    override fun getItemCount(): Int = trackList.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

}