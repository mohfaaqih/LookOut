package com.example.sharedpref


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*
import kotlin.random.Random


class Start : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()



        btn_start.setOnClickListener {
            val uniqueInt = Random.nextInt(9999999)

            var id = uniqueInt.toString()

            val sharedPreference =  getSharedPreferences(
                "app_preference", Context.MODE_PRIVATE
            )

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("result", id)
            startActivity(intent)
        }
    }
}




