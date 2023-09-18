package com.cst3104.id41080471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_linear)

        val toast_message = findViewById(R.id.button3) as Button
        toast_message.setOnClickListener {
            Toast.makeText(this@MainActivity, "@strings/toast_message", Toast.LENGTH_LONG).show()
        }
    }

}