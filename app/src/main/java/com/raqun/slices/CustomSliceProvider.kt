package com.raqun.slices

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.list
import androidx.slice.builders.row

class CustomSliceProvider : SliceProvider() {

    override fun onCreateSliceProvider(): Boolean = true

    override fun onBindSlice(sliceUri: Uri): Slice? {

        context?.let {
            val sliceAction = SliceActionFactory.createHelloSlicesAction(context)

            return if (sliceUri.path == "/slice") {
                list(context, sliceUri, ListBuilder.INFINITY)
                {
                    row {
                        title = "Hello Slices Demo"
                        primaryAction = sliceAction
                    }
                }
            } else {
                list(context, sliceUri, ListBuilder.INFINITY)
                {
                    row {
                        title = "Sorry! Uri not found!"
                        primaryAction = sliceAction
                    }
                }
            }
        }

        return null
    }

    override fun onMapIntentToUri(intent: Intent?): Uri {
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)

        if (intent == null) return uriBuilder.build()

        val data = intent.data
        if (data != null && data.path != null) {
            val path = data.path.replace("/slice", "")
            uriBuilder = uriBuilder.path(path)
        }

        if (context != null) {
            uriBuilder = uriBuilder.authority(context.packageName)
        }

        return uriBuilder.build()
    }

}