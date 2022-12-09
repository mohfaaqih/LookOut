package com.example.sharedpref

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SecondActivity : AppCompatActivity() {
    lateinit var labelHeader : TextView
    lateinit var listTodo : ListView
    lateinit var reccylerview :RecyclerView

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV6b3l6c2t5ZmprZXhnaG5jcGdpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NjU3MTU2MTMsImV4cCI6MTk4MTI5MTYxM30.Fkha5IFzFupCFw2RUsECLSb37U_AhvFaIM8mnicL4qo"
    val token = "Bearer $apiKey"

    val Items = ArrayList<Model>()
    val todoApi = RetrofitHelper.getInstance().create(TodoApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        labelHeader = findViewById(R.id.label_header)
        reccylerview = findViewById(R.id.list_todo)

        val result = intent.getStringExtra("result")
        labelHeader.text = "What's up, $result?"

        CoroutineScope(Dispatchers.Main).launch {
            val response = todoApi.get(token=token, apiKey=apiKey)

            response.body()?.forEach {
                Items.add(
                    Model(
                        Id=it.id,
                        Title=it.title,
                        Description=it.description
                    )
                )
            }

            setList(Items)
        }

    }

    fun setList(Items: ArrayList<Model>) {
        val adapter = Adapter(this, R.layout.cv, Items)
        reccylerview.adapter = adapter
    }
}