package com.example.anteeoneapp.repository

import com.example.anteeoneapp.R
import com.example.anteeoneapp.models.Track

object TrackRepository {

    var idCounter=0;

    val tracksList: ArrayList<Track> = arrayListOf(
        Track(idCounter++,"Белая Стрекоза Любви","Gachi", R.drawable.gachi,R.raw.gachi),
        Track(idCounter++,"Темные Мысли","AnteeOne", R.drawable.dt,R.raw.dt),
        Track(idCounter++,"JoJo","Jojo OST",R.drawable.jojo,R.raw.jojo),
        Track(idCounter++,"Cadillac","MORGENTSHTERN",R.drawable.cdl,R.raw.cdl),
        Track(idCounter++,"По глазам","Slava Marlow",R.drawable.slava,R.raw.slava),
        Track(idCounter++,"ICE","MORGENTSHTERN",R.drawable.ice,R.raw.ice),
        Track(idCounter++,"AUF","Nurminckiy",R.drawable.auf,R.raw.auf),
        Track(idCounter++,"Pososi","MORGENTSHTERN",R.drawable.pososi,R.raw.pososi),
        Track(idCounter++,"Новый Мерин","MORGENTSHTERN",R.drawable.merin,R.raw.merin),
        Track(idCounter++,"Мне пох","MORGENTSHTERN",R.drawable.poh,R.raw.poh),
        Track(idCounter++,"Темные Мысли","AnteeOne", R.drawable.dt,R.raw.dt),
        Track(idCounter++,"JoJo","Jojo OST",R.drawable.jojo,R.raw.jojo),
        Track(idCounter++,"Cadillac","MORGENTSHTERN",R.drawable.cdl,R.raw.cdl),
        Track(idCounter++,"По глазам","Slava Marlow",R.drawable.slava,R.raw.slava)

    )
}