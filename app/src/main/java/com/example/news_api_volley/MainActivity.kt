package com.example.news_api_volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          android.os.Handler(Looper.getMainLooper()).postDelayed({},100)


    }
}