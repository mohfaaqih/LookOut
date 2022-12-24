package com.example.sharedpref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager


class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        var session_id = sharedPreference.getString("session_id", "").toString()

        Handler().postDelayed({
            if (session_id.isEmpty()) {
                startActivity(Intent(this, FirstActivity::class.java))
            }
            else {
                startActivity(Intent(this, start::class.java))
            }
            finish()
        }, 3000)
    }
}