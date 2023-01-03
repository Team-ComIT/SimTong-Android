@file:Suppress(
    "MissingFirebaseInstanceTokenRefresh",
    "MagicNumber",
)

package com.comit.simtong.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.comit.simtong.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMManager : FirebaseMessagingService() {

    private companion object Channel {
        const val CHANNEL_ID = "simtong_fcm"
        const val CHANNEL_NAME = "SimTong"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            sendNotification(remoteMessage)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val channelMessage = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channelMessage.apply {
                enableLights(true)
                enableVibration(true)
                setShowBadge(false)
                vibrationPattern = longArrayOf(100, 200, 100, 200)
            }
            notificationChannel.createNotificationChannel(channelMessage)

            val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setChannelId(CHANNEL_ID)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(9999, notificationBuilder.build())
        } else {
            val notificationBuilder = NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())
        }
    }
}
