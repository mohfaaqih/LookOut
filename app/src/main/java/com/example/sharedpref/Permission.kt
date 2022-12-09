package com.example.sharedpref

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class Permission : AppCompatActivity() {

    private lateinit var permissionLauncer: ActivityResultLauncher<Array<String>>
    private var isReadPermissionGrantet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)


    }
}