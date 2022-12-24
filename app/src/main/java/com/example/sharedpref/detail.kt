package com.example.sharedpref


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.sharedpref.api.RetrofitHelper
import com.example.sharedpref.api.TodoApi
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class detail : AppCompatActivity() {

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Impsc3N2cmV0cGJ6c25uYmZwaWpkIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1ODMwMDUsImV4cCI6MTk4NjE1OTAwNX0.XzG93lsdOa4ap-NMu1cgquhdmywh1924-WnuM9f3fXs"
    val token = "Bearer $apiKey"

    val todoApi = RetrofitHelper.getInstance().create(TodoApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()


        val sharedPreferences = getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )
                val ssid = sharedPreferences.getString("session_id", "[No session ID found]").toString()

        var intent = intent
        val aUsername = intent.getStringExtra("iUsername")
        val aDescription = intent.getStringExtra("iDescription")
        val id = intent.getStringExtra("ID")
        val session_id = intent.getStringExtra("iSSID")

        username.text = aUsername
        description.text = aDescription

        fun deleteItem(id: String) {
            CoroutineScope(Dispatchers.Main).launch {
                todoApi.delete(token=token, apiKey=apiKey, idQuery=id)


            }
        }

        delete.setOnClickListener(){
            if (session_id == ssid) {
                var queryId = "eq.$id"
                deleteItem(queryId)

                Toast.makeText(
                    applicationContext,
                    "Delete Post ",
                    Toast.LENGTH_SHORT
                ).show()

            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            } else {
                return@setOnClickListener
            }
        }

    }
}