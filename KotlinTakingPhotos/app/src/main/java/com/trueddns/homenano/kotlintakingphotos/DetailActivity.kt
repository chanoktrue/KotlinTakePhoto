package com.trueddns.homenano.kotlintakingphotos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DetailActivity : AppCompatActivity() {

    val showImageView: ImageView by lazy {
        findViewById(R.id.showImageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val img = intent.getParcelableExtra<Bitmap>("image")
        showImageView.setImageBitmap(img)

    }
}


