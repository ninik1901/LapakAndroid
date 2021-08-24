package com.example.projek.helper


import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: " + remoteMessage.from)
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data.size)
            val message = remoteMessage.data["body"]
            val title = remoteMessage.data["title"]
            val jenis = remoteMessage.data["jenis"]

            if (message != null) {
                if (title != null) {
                    if (jenis != null) {
                        if (jenis.equals("biasa")) {
                            showNotificationBiasa(message, title, this)

                        } else {
                            showNotification(message, title, jenis, this)

                        }
                    }
                }
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {

            val message = remoteMessage.notification!!.body
            val title = remoteMessage.notification!!.title


            if (title != null) {
                if (message != null) {
                }
            }
        }
    }

    private fun showNotification(
        title: String,
        message: String,
        jenis: String,
        context: Context
    ) {

        NotificationUtils(context).showNotification(
            title,
            message,
            jenis
        )
    }

    private fun showNotificationBiasa(
        title: String,
        message: String,
        context: Context

    ) {
        NotificationUtils(context).showNotificationBiasa(
            title,
            message

        )
    }

}
