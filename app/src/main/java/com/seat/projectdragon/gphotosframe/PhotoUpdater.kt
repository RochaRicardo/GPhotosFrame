package com.seat.projectdragon.gphotosframe

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import java.util.*

class PhotoUpdater : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                //your method
                val man = AppWidgetManager.getInstance(applicationContext)
                val ids = man.getAppWidgetIds(
                    ComponentName.createRelative(applicationContext, PhotoShow::class.java.name)
                )
// 3
                val updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
// 4
                updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
// 5
                sendBroadcast(updateIntent)
            }
        }, 0, 120000)//
        return super.onStartCommand(intent, flags, startId)
    }
}
