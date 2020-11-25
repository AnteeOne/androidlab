package com.example.anteeoneapp.ui.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.anteeoneapp.R
import com.example.anteeoneapp.models.Track
import com.example.anteeoneapp.repository.TrackRepository
import com.example.anteeoneapp.services.MusicService

class TrackDetailFragment : BaseFragment(R.layout.fragment_track_detail) {

    private lateinit var mTrack: Track
    private lateinit var titleView: TextView
    private lateinit var authorView: TextView
    private lateinit var coverView: ImageView

    private lateinit var previousButton: ImageView
    private lateinit var playButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var pauseButton:ImageView

    private var musicService: MusicService? = null

    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if (musicService==null) Log.println(Log.DEBUG,"mytag","service is null")
            else Log.println(Log.DEBUG,"mytag","service is not null")
        }

        override fun onServiceDisconnected(className: ComponentName) {
            musicService = null
            Log.println(Log.DEBUG,"mytag","set service to null")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initService()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        initView()
    }

    override fun onStart() {
        super.onStart()

    }


    private fun initFields() {
        titleView = mRootView.findViewById(R.id.fragment_track_detail_title)
        authorView = mRootView.findViewById(R.id.fragment_track_detail_author)
        coverView = mRootView.findViewById(R.id.fragment_track_detail_cover)
        previousButton = mRootView.findViewById(R.id.fragment_track_detail_navigation_previous)
        playButton = mRootView.findViewById(R.id.fragment_track_detail_navigation_play)
        nextButton = mRootView.findViewById(R.id.fragment_track_detail_navigation_next)
        pauseButton = mRootView.findViewById(R.id.fragment_track_detail_navigation_pause)

    }

    private fun initView() {
        val id = arguments?.getInt("id")
        id?.let {
            mTrack = TrackRepository.tracksList[id]

            titleView.text = mTrack.title
            authorView.text = mTrack.author
            coverView.setImageResource(mTrack.cover)
            initMusicNavigationView(id)

        }
    }

    private fun initMusicNavigationView(id: Int) {

        playButton.setOnClickListener {
            Log.println(Log.DEBUG,"mytag", musicService!!.currentTrackId.toString());
            musicService?.playTrack()
            showPauseButton()
        }
        previousButton.setOnClickListener {
            musicService?.playPreviousTrack()
            updateView(musicService?.currentTrackId?:0)
        }
        nextButton.setOnClickListener {
            musicService?.playNextTrack()
            updateView(musicService?.currentTrackId?:0)
        }
        pauseButton.setOnClickListener {
            musicService?.pauseTrack()
            showPlayButton()
        }
    }

    private fun updateView(id:Int){
        id.let {
            mTrack = TrackRepository.tracksList[id]

            titleView.text = mTrack.title
            authorView.text = mTrack.author
            coverView.setImageResource(mTrack.cover)

            showPauseButton()
            playButton.setOnClickListener {
                musicService?.playTrack()
                showPauseButton()
            }

        }
    }

    private fun initService(){
        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, binderConnection, Context.BIND_AUTO_CREATE)
    }

    private fun showPauseButton(){
        playButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
    }

    private fun showPlayButton(){
        playButton.visibility = View.VISIBLE
        pauseButton.visibility = View.GONE
    }


}