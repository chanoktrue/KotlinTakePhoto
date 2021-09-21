package com.trueddns.homenano.kotlintakingphotos

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.graphics.BitmapCompat
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    val takePictrueButton: Button by lazy {
        findViewById(R.id.takePictureButton)
    }

    val imageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    val showButton: Button by lazy {
        findViewById(R.id.showButton)
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            getPhoto(data)
        }
    }

    var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takePictrueButton.setOnClickListener {
            takePhoto()
        }

        showButton.setText("Show Picture")
        showButton.setOnClickListener {
            tapShowButton()
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startForResult.launch(intent)
    }

    private fun getPhoto(data: Intent?) {
        bitmap = data?.extras?.get("data") as Bitmap
        imageView.setImageBitmap(bitmap)
    }

    private  fun tapShowButton() {

        if (bitmap == null) return

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("image", bitmap)
        startActivity(intent)

    }

}


