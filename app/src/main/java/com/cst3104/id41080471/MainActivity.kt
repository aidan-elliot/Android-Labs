package com.cst3104.id41080471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_linear)

        val toastmessage = findViewById<Button>(R.id.button3)
        toastmessage.setOnClickListener {
            Toast.makeText(this@MainActivity, R.string.toast_message, Toast.LENGTH_LONG).show()
        }
    }

}