package com.seat.projectdragon.gphotosframe

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            val man = AppWidgetManager.getInstance(this)
// 2
            val ids = man.getAppWidgetIds(
                ComponentName(this, PhotoShow::class.java)
            )
// 3
            val updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
// 4
            updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
// 5
            sendBroadcast(updateIntent)
        }

        startService(Intent(this, PhotoUpdater::class.java))
    }
}
