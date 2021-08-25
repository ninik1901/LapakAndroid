package com.example.projek.helper


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.projek.DonasiBukuCetak
import com.example.projek.DonasiEbook
import com.example.projek.MainActivity
import com.example.projek.R

class NotificationUtils(var context: Context) {
    private var i: Intent = Intent(context.applicationContext, MainActivity::class.java)
    private var p: PendingIntent =
        PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)

    fun showNotification(
        title: String?,
        message: String?,
        jenis: String?
    ) {


        when {
            jenis.equals("pengajuan_buku") -> {
                i = Intent(context.applicationContext, DonasiBukuCetak::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                p =
                    PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)

            }
            jenis.equals("pengajuan_ebook") -> {
                i = Intent(context.applicationContext, DonasiEbook::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                p =
                    PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)
            }
            jenis.equals("jadwal_baru") -> {
                i = Intent(context.applicationContext, MainActivity::class.java)
                i.putExtra("jadwal", "jadwal_baru")
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                p =
                    PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)
            }


            // Generate random integers in range 0 to 999

            //        String channelId = context.getString(R.string.default_notification_channel_id);

            //        Uri r = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }


        // Generate random integers in range 0 to 999

        //        String channelId = context.getString(R.string.default_notification_channel_id);
        val channelId = "1"

//        Uri r = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        val VIBRATE_PATTERN = longArrayOf(0, 500)
        val notification = NotificationCompat.Builder(context, channelId).also {
            it.setColor(ContextCompat.getColor(context, R.color.biruMuda))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setVibrate(VIBRATE_PATTERN)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(p)
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Dafult Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.setShowBadge(false)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(channelId.toInt(), notification.build())
    }

    fun showNotificationBiasa(
        title: String?,
        message: String?
    ) {
        val channelId = "1"
        //        Uri r = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        val VIBRATE_PATTERN = longArrayOf(0, 500)
        val notification =
            NotificationCompat.Builder(context, channelId)
        notification.setColor(ContextCompat.getColor(context, R.color.biruMuda))
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setVibrate(VIBRATE_PATTERN).priority = NotificationCompat.PRIORITY_HIGH
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Dafult Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.setShowBadge(false)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification.build())
    }

}