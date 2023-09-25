package com.cst3104.id41080471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_linear)

        val toastmessage = findViewById<Button>(R.id.button3)
        toastmessage.setOnClickListener {
            Toast.makeText(this@MainActivity, R.string.toast_message, Toast.LENGTH_LONG)
                .show()
        }
        val switch = findViewById<Switch>(R.id.switch1)

        switch.setOnCheckedChangeListener { _, isChecked ->
            val message = "${R.string.switchState} ${if (isChecked) R.string.On else R.string.Off}"
            showSnackbar(message)
        }
    }
    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        snackbar.setAction(R.string.undoString, View.OnClickListener {

        })
        snackbar.show()
    }

}