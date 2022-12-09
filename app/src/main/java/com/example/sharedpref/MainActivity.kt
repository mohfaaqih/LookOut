package com.example.sharedpref

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpref.PreferenceHelper.clearValues
import com.example.sharedpref.PreferenceHelper.customPreference
import com.example.sharedpref.PreferenceHelper.defaultPreference
import com.example.sharedpref.PreferenceHelper.password
import com.example.sharedpref.PreferenceHelper.userId


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val CUSTOM_PREF_NAME = "User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnShow.setOnClickListener(this)
        btnShowDefault.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val prefs = customPreference(this, CUSTOM_PREF_NAME)
        when (v?.id) {
            R.id.btnSave -> {
                prefs.password = inPassword.text.toString()
                prefs.userId = inUserId.text.toString().toInt()
            }
            R.id.btnClear -> {
                prefs.clearValues

            }
            R.id.btnShow -> {
                inUserId.setText(prefs.userId.toString())
                inPassword.setText(prefs.password)
            }
            R.id.btnShowDefault -> {

                val defaultPrefs = defaultPreference(this)
                inUserId.setText(defaultPrefs.userId.toString())
                inPassword.setText(defaultPrefs.password)
            }
        }
    }


}

object PreferenceHelper {

    val USER_ID = "USER_ID"
    val USER_PASSWORD = "PASSWORD"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.userId
        get() = getInt(USER_ID, 0)
        set(value) {
            editMe {
                it.putInt(USER_ID, value)
            }
        }

    var SharedPreferences.password
        get() = getString(USER_PASSWORD, "")
        set(value) {
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}

