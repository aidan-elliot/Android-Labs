package com.cst3104.id41080471

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.Button
import android.widget.EditText
import android.view.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
            val loginButton: Button = findViewById(R.id.loginbutton)

            val prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE)
            val savedEmail = prefs.getString("LoginName", "")
            emailEditText.setText(savedEmail)

            loginButton.setOnClickListener {
                val emailAddress = emailEditText.text.toString()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("EmailAddress", emailAddress)

                // Save the email to SharedPreferences
                val editor = prefs.edit()
                editor.putString("LoginName", emailAddress)
                editor.apply()

                startActivity(intent)
            }
        }
    override fun onStart() {
        super.onStart()
        Log.w(TAG, "onStart() Called when activity becomes visible to app user")
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume() Called when user begins interacting with app")
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause() Called when current activity is paused and prev. is resumed")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG, "onStop() Called when activity is no longer visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "onDestroy() called before an activity is destroyed")
    }
}
