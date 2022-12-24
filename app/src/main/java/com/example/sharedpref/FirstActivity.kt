package com.example.sharedpref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_start.*
import java.util.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_first)
        supportActionBar?.hide()

        btn_get.setOnClickListener {
        val session_id: String = UUID.randomUUID().toString()

        val sharedPreference = getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )
        var editor = sharedPreference.edit()
        editor.putString("session_id", session_id)
        editor.commit()

        val intent = Intent(this, start::class.java)
        intent.putExtra("session_id", session_id)
        startActivity(intent)
    }

    }
}