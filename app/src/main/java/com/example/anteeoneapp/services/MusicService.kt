package com.example.anteeoneapp.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.anteeoneapp.R
import com.example.anteeoneapp.models.Track
import com.example.anteeoneapp.notifications.NotificationController
import com.example.anteeoneapp.repository.TrackRepository

class MusicService() : Service() {

    private val CHANNEL_ID = "antee_music"
    private val notificationId = 1

    private lateinit var mPlayer: MediaPlayer
    var currentTrackId: Int? = null
    lateinit var trackList: ArrayList<Track>
    private lateinit var musicBinder: MusicBinder;

    private lateinit var notificationController:NotificationController


    inner class MusicBinder : Binder() {

        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent): IBinder = musicBinder

    override fun onCreate() {
        super.onCreate()
        initFields()

        initNotificationBar()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mPlayer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()
    }

    private fun initFields() {
        currentTrackId = 0
        mPlayer = MediaPlayer()
        musicBinder = MusicBinder()
        trackList = TrackRepository.tracksList
    }

    private fun initNotificationBar(){
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager.getNotificationChannel(CHANNEL_ID) ?:  NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
        val track = TrackRepository.tracksList[currentTrackId?:0]
        Log.println(Log.DEBUG,"mytag","am here!")
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(track.title)
                .setContentText(track.author)

        val notification:Notification = builder.build()

        Log.println(Log.DEBUG,"mytag",notification.toString())
        notificationManager.notify(notificationId,notification)


    }



    // track control

    fun playPreviousTrack() {
        currentTrackId?.let {
            currentTrackId = if (it == 0) {
                trackList.size - 1
            } else {
                it - 1
            }
            setTrack(currentTrackId ?: 0)
            playTrack()
        }
    }

    fun playNextTrack() {
        currentTrackId?.let {
            currentTrackId = if (it == trackList.size - 1) {
                0
            } else {
                it + 1
            }
            setTrack(currentTrackId ?: 0)
            playTrack()
        }
    }

    fun pauseTrack() {
        mPlayer.pause()
    }

    fun playTrack() {
        mPlayer.start()
    }

    fun setTrack(id: Int) {
        if (mPlayer.isPlaying) mPlayer.stop()
        mPlayer = MediaPlayer.create(applicationContext, trackList[id].sound)
        currentTrackId = id
        mPlayer.run {
            setOnCompletionListener {
                stop()
            }
        }
    }


}
