package com.example.anteeoneapp.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.anteeoneapp.R
import com.example.anteeoneapp.repository.TrackRepository
import com.example.anteeoneapp.services.MusicService
import androidx.media.app.NotificationCompat.MediaStyle as NotificationCompatMediaStyle

class NotificationController(val context:Context){

    private val CHANNEL_ID = "antee_music"
    private val notificationId = 1

    private var notificationManager: NotificationManager? = null
    private lateinit var nBuilder:NotificationCompat.Builder
    private var currentTrackId:Int? = null

    init{
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager?.getNotificationChannel(CHANNEL_ID) ?:  NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager?.createNotificationChannel(channel)
        }

        val previousIntent = Intent(context,MusicService::class.java).apply {
            action = "PREVIOUS"
        }
        val resumeIntent = Intent(context,MusicService::class.java).apply {
            action = "RESUME"
        }
        val nextIntent = Intent(context,MusicService::class.java).apply {
            action = "NEXT"
        }
        val previousPendingIntent = PendingIntent.getService(context,0,previousIntent,0)
        val resumePendingIntent = PendingIntent.getService(context,1,resumeIntent,0)
        val nextPendingIntent = PendingIntent.getService(context,2,nextIntent,0)

        nBuilder = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.play_arrow_24px)
            .addAction(R.drawable.skip_previous_24px,"Previous",previousPendingIntent)
            .addAction(R.drawable.games_24px,"Pause",resumePendingIntent)
            .addAction(R.drawable.skip_next_24px,"Next",nextPendingIntent )
            .setNotificationSilent()

    }



    fun build(trackId:Int){
        val track = TrackRepository.tracksList[trackId]
        val cover = BitmapFactory.decodeResource(context.resources,track.cover)

        val builder = nBuilder
                .setContentTitle(track.title)
                .setContentText(track.author)
                .setStyle(NotificationCompatMediaStyle())
                .setLargeIcon(cover)

        val notification: Notification = builder.build()
        notificationManager?.notify(notificationId,notification)
    }


    fun playOrPause(){

    }

}