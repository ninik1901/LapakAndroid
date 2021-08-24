package com.example.projek.helper


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.media.RingtoneManager.getDefaultUri
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.projek.MainActivity
import com.example.projek.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 * Created by Dhimas Panji Sastra on
 * Copyright (c)  . All rights reserved.
 * Last modified $file.lastModified
 * Made With ❤ for U
 */
class FirebaseNotification : FirebaseMessagingService() {
    var TAG = "FIREBASE MESSAGING"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: " + remoteMessage.from)
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data.size)
            val message = remoteMessage.data["body"]
            val title = remoteMessage.data["title"]
            val driver = remoteMessage.data["driver"]

            issueNotification(message, title)
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {

            val message = remoteMessage.notification!!.body
            val title = remoteMessage.notification!!.title


            issueNotification(message, title)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun makeNotificationChannel() {
        val channel = NotificationChannel(
            "1",
            "Rumah Baca",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.setShowBadge(true) // set false to disable badges, Oreo exclusive
        val notificationManager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        notificationManager.createNotificationChannel(channel)
    }

    fun issueNotification(message: String?, title: String?) {
        val requestID = System.currentTimeMillis().toInt()
        // make the channel. The method has been discussed before.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeNotificationChannel()
        }
        // the check ensures that the channel will only be made
        // if the device is running Android 8+
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(this, "Rumah Baca")
        // the second parameter is the channel id.
        // it should be the same as passed to the makeNotificationChannel() method
        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val alarmSound: Uri = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val contentIntent = PendingIntent.getActivity(
            this,
            requestID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notification
            .setSmallIcon(R.drawable.logo) // can use any other icon
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(contentIntent)
            .setSound(alarmSound)
            .setNumber(1) // this shows a number in the notification dots
        val notificationManager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        notificationManager.notify(1, notification.build())
        // it is better to not use 0 as notification id, so used 1.
    }
}
