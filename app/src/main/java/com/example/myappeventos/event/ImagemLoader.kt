package com.example.myappeventos.event

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImagemLoader {
    /**
     * Dowload the image from internet,
     * and then display into eventImage view.
     */
    fun loadImageFromUrl (
        url: String,
        eventImage: ImageView,
        context: Context?
    ){
        Log.d("Loading image", "and displaying it!")
        Picasso.with(context)
            .load(url)
            .into(eventImage)
    }
}