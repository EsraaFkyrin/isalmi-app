package com.example.isalmiapp.player

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import com.example.isalmiapp.MyApplication
import com.example.isalmiapp.R

class PlayerService :Service() {
     val  binder=MYBinder()
    var mediaPlayer=MediaPlayer()
    override fun onBind(intent: Intent?): IBinder? {
     return binder
    }

    inner class MYBinder:Binder(){
        fun getServices():PlayerService{
            return this@PlayerService
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        val urlToPlay=intent?.getStringExtra("url")
        val name=intent?.getStringExtra("name")
        val action=intent?.getStringExtra("action")

        if(urlToPlay!=null&&name!=null)
            startMediaPlayer(urlToPlay,name)

        if (action!=null)
            Log.e("acion",action)

        if (action.equals("play")){
            playPausePlayer()

        }
        else if (action.equals("stop")){
            stopMediaPlayer()
        }
        return START_NOT_STICKY
    }

var name:String=""

    fun startMediaPlayer(urlToPlay: String, name: String) {
         pauseMediaPlayer()
        this.name=name
        mediaPlayer= MediaPlayer()
        mediaPlayer.setDataSource(this, Uri.parse(urlToPlay))
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            it.start()

        }
        createNotificationForMediaPlayer(name)

    }

    fun pauseMediaPlayer() {
    if (mediaPlayer.isPlaying)
        mediaPlayer.pause()
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()

        }
        stopForeground(true)
        stopSelf()
    }

    private fun playPausePlayer() {
        Log.e("action","playerPause")
        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
        else if (!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }
        updateNotification()
    }

    //create Notification
    @SuppressLint("RemoteViewLayout")
    private  fun createNotificationForMediaPlayer(name: String){
        val defaultView=RemoteViews(packageName, R.layout.notificationview)
        defaultView.setTextViewText(R.id.title,"Islami App Radio")
        defaultView.setTextViewText(R.id.description,name)
        defaultView.setImageViewResource(R.id.play,
        if (mediaPlayer.isPlaying)R.drawable.ic_stop
        else R.drawable.play
            )
        defaultView.setOnClickPendingIntent(R.id.play,getPlayButtonPendingIntent())
        defaultView.setOnClickPendingIntent(R.id.stop,getStopPendingIntent())

        var builder=NotificationCompat.Builder(this,MyApplication.Radio_Player_Notification_Channel)
            .setSmallIcon(R.drawable.ic_notification)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(defaultView)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSound(null)


        startForeground(20,builder.build())
    }


    private fun updateNotification(){
        val defaultView=RemoteViews(packageName, R.layout.notificationview)
        defaultView.setTextViewText(R.id.title,"Islami Notification Radio")
        defaultView.setTextViewText(R.id.description,name)
        defaultView.setImageViewResource(R.id.play,
            if (mediaPlayer.isPlaying)R.drawable.ic_stop
            else R.drawable.play)

        defaultView.setOnClickPendingIntent(R.id.play,getPlayButtonPendingIntent())
        defaultView.setOnClickPendingIntent(R.id.stop,getStopPendingIntent())
        var builder=NotificationCompat.Builder(this,"Islami")
            .setSmallIcon(R.drawable.ic_notification)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(defaultView)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setDefaults(0)
            .setSound(null)

         val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE)
                 as NotificationManager
         notificationManager.notify(20,builder.build())



    }
    val play_Action="play"
    val stop_Action="stop"
  private fun getPlayButtonPendingIntent():PendingIntent{
      val intent=Intent(this,PlayerService::class.java)
      intent.putExtra("action",play_Action)
      val pendingIntent=PendingIntent.getService(
     this,12,intent,PendingIntent.FLAG_IMMUTABLE)

      return pendingIntent
  }


    private fun getStopPendingIntent():PendingIntent{
        val intent=Intent(this,PlayerService::class.java)
        intent.putExtra("action",stop_Action)
        val pendingIntent=PendingIntent.getService(
            this,0,intent,PendingIntent.FLAG_IMMUTABLE)

        return pendingIntent
    }



}