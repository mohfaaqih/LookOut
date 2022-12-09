package com.example.sharedpref

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.ContextCompat
import com.example.sharedpref.databinding.ActivityStorageBinding
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList

class StorageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorageBinding;
    lateinit var ImageUri: Uri

    private lateinit var permissionLauncer: ActivityResultLauncher<Array<String>>
    private var isReadPermissionGrantet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        fun requestPermission(){

            isReadPermissionGrantet = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED

            val permissionRequest : MutableList<String> = ArrayList()

            if(!isReadPermissionGrantet){
                permissionRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            if (permissionRequest.isNotEmpty()){

                permissionLauncer.launch(permissionRequest.toTypedArray())
            }

        }

        binding.selectImageBtn.setOnClickListener {

            selectImage()
        }
        binding.uploadImageBtn.setOnClickListener {

            uploadImage()
        }
    }

    private fun uploadImage() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("uploading file...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_mm_dd_hh_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("image/$fileName")

        storageReference.putFile(ImageUri).addOnSuccessListener {

            binding.firebaseImage.setImageURI(null)
            Toast.makeText(this@StorageActivity, "successfuly uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()


        }.addOnFailureListener {

            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@StorageActivity, "failed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun selectImage() {

        val intent = Intent()
        intent.type = "images/"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {

            ImageUri = data?.data!!
            binding.firebaseImage.setImageURI(ImageUri)

        }
    }
}
