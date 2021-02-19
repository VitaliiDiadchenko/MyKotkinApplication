package com.vitaliidiadchenko.mykotkinapplication.work_manager

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.vitaliidiadchenko.mykotkinapplication.MainActivity
import com.vitaliidiadchenko.mykotkinapplication.R
import com.vitaliidiadchenko.mykotkinapplication.data.Movie

class BuildNotification(private val context: Context) {

    companion object {
        private const val CHANNEL_TOP_MOVIE = "top_movie"
        private const val NOTIFICATION_TITLE = "Highest rated movie"
        private const val REQUEST_CONTENT = 1
        private const val TAG = "movie"
    }

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    private fun initialize() {
        if (notificationManagerCompat.getNotificationChannel(CHANNEL_TOP_MOVIE) == null) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannelCompat.Builder(
                    CHANNEL_TOP_MOVIE,
                    NotificationManagerCompat.IMPORTANCE_HIGH
                ).setName(context.getString(R.string.channel_top_movie))
                    .setDescription(context.getString(R.string.channel_top_movie_description))
                    .build()
            )
        }
    }

    fun showNotification(movie: Movie) {
        initialize()

        val contentUri = "https://www.themoviedb.org/movie/${movie.id}".toUri()

        val notification = NotificationCompat.Builder(context, CHANNEL_TOP_MOVIE)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(movie.title)
            .setSmallIcon(R.drawable.ic_message)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    REQUEST_CONTENT,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
        notificationManagerCompat.notify(TAG, movie.id, notification.build())
    }
}



