package com.raqun.slices

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction

class SliceActionFactory {
    companion object {
        fun createHelloSlicesAction(context: Context,
                                    requestCode: Int = 0,
                                    flags: Int = 0) {
            SliceAction.create(
                    PendingIntent.getActivity(
                            context,
                            requestCode,
                            Intent(context, MainActivity::class.java),
                            flags),
                    IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground),
                    ListBuilder.ICON_IMAGE,
                    "Hello Slices")
        }
    }
}