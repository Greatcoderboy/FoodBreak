package com.prince.foodbreak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.prince.foodbreak.R.layout

class MainActivity : AppCompatActivity() {
   //Splash Screen Timer
    private val SPLASH_TIME_OUT = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.splash_screen)
        Handler().postDelayed(
            {
                val i = Intent(this@MainActivity,OpenActivity::class.java)
                startActivity(i)
                finish()

            },SPLASH_TIME_OUT
        )




        }
    }

