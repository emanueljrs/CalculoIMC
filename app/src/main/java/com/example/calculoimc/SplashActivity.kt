package com.example.calculoimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 5000L
    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Atribui as animações top_animation e bottom_animation nas variáveis
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        //Atribui as animações nas Views da tela
        img_balance.animation = topAnimation
        text_tittle_splash.animation = bottomAnimation
        text_description_splash.animation = bottomAnimation

        //Chama a activity Main depois do tempo definido na constante SPLASH_SCREEN
        Handler().postDelayed({
           val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN)
    }
}