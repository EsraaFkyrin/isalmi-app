package com.example.isalmiapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    companion object{
        val Radio_Player_Notification_Channel="Radio_Channel"
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name=getString(R.string.radio_player_channel)
            val descriptionText=getString(R.string.radio_player_channel)
            val importance=NotificationManager.IMPORTANCE_LOW
            val channel=NotificationChannel(Radio_Player_Notification_Channel,name,importance)
                .apply {
                    description=descriptionText
                }
            val notificationManager:NotificationManager=
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            notificationManager.createNotificationChannel(channel)
        }
    }

}