package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var TextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        imageView=findViewById(R.id.imagelogo)
        var animatfade=AnimationUtils.loadAnimation(applicationContext,R.anim.alphafade)
        imageView.animation=animatfade
        TextView=findViewById(R.id.splashtext)
        var animatmove=AnimationUtils.loadAnimation(applicationContext,R.anim.movement)
        TextView.animation=animatmove
        Handler().postDelayed({
            var intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}