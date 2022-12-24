package com.example.sharedpref


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*




class start : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()

        btn_start.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        var session_id = sharedPreference.getString("session_id", "").toString()

        if (session_id.isEmpty()) {
            super.onBackPressed()
            return
        }

        Toast.makeText(
            applicationContext,
            "you are in session",
            Toast.LENGTH_SHORT
        ).show()
    }
}




