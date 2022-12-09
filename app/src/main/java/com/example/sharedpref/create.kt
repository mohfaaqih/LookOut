package com.example.sharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class create : AppCompatActivity() {

    lateinit var btnSubmit : Button
    lateinit var etTitle : EditText
    lateinit var etDescription : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        supportActionBar?.hide()

        etTitle = findViewById(R.id.et_title_create_todo)
        etDescription = findViewById(R.id.et_description_create_todo)
        btnSubmit = findViewById(R.id.btn_submit_create_todo)

        btnSubmit.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val data = TodoData(title = etTitle.text.toString(), description = etDescription.text.toString())


                Toast.makeText(
                    applicationContext,
                    "Berhasil membuat Todo!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}