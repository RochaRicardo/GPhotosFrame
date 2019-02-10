
package com.seat.projectdragon.gphotosframe

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Bitmap
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Implementation of App Widget functionality.
 */
class PhotoShow : AppWidgetProvider() {
    val photos: IntArray = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image11,
        R.drawable.image10
    )
    val numbers: MutableList<Int> = mutableListOf(0, 1, 2,3,4,5,6,7,8,9)
    var counter =0

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        if(counter ==10){
            counter =0
            numbers.shuffle()
        }
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, photos[numbers[counter]])
        }
        counter++
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        numbers.shuffle()
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int,
            photoValue: Int
        ) {

            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.photo_show)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            val awt: AppWidgetTarget = object : AppWidgetTarget(context.applicationContext, R.id.widgetImageView, views, appWidgetId) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                }
            }

            var options = RequestOptions().
                override(300, 300).placeholder(photoValue).error(R.drawable.image2)

            Glide.with(context).asBitmap().load(photoValue).apply(options).into(awt)
//
//            var bitmap = Glide
//                .with(context.getApplicationContext())
//                .asBitmap()
//                .load(R.drawable.image2)
//                .submit().get()
//
//            views.setImageViewBitmap(R.id.widgetImageView, bitmap)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

