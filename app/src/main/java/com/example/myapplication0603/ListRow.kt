package com.example.myapplication0603

import android.graphics.Bitmap

class ListRow(thumbnail: Bitmap?, title: String, text: String) {
    var thumbnail: Bitmap? = null
    var title: String
    var text: String

    init {
        this.thumbnail = thumbnail
        this.title = title
        this.text = text
    }
}