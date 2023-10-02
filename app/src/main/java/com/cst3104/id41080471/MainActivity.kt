package com.cst3104.id41080471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flag = findViewById<ImageView>(R.id.flag)
        val switch = findViewById<Switch>(R.id.spin_switch)



        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)

                flag.startAnimation(rotate)
            }else{
                flag.clearAnimation()
            }
        }
    }
}