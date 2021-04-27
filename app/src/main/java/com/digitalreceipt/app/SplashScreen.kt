package com.digitalreceipt.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        imageView=findViewById(R.id.imagelogo)
        var animat=AnimationUtils.loadAnimation(applicationContext,R.anim.alphafade)
        imageView.animation=animat
    }
}