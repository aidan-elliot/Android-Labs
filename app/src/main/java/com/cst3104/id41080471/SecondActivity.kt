package com.cst3104.id41080471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import android.app.Activity
import android.graphics.Bitmap
import android.provider.MediaStore
import java.io.FileOutputStream
import android.content.Context



class SecondActivity : AppCompatActivity() {

    private val cameraResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            val imageView: ImageView = findViewById(R.id.imageView2)
            imageView.setImageBitmap(imageBitmap)

            // TODO: Save the bitmap to file if required
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val fromPrevious = intent
        val receivedEmailAddress = fromPrevious.getStringExtra("EmailAddress")

        val phoneNumberEditText: EditText = findViewById(R.id.editTextPhone)
        val callButton: Button = findViewById(R.id.button)
        val cameraButton: Button = findViewById(R.id.button2)

        val prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE)
        val savedPhoneNumber = prefs.getString("PhoneNumber", "")
        phoneNumberEditText.setText(savedPhoneNumber)

        callButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }

        cameraButton.setOnClickListener {
            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResultLauncher.launch(cameraIntent)
        }
    }

    override fun onPause() {
        super.onPause()
        val phoneNumberEditText: EditText = findViewById(R.id.editTextPhone)
        val phoneNumber = phoneNumberEditText.text.toString()

        val prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("PhoneNumber", phoneNumber)
        editor.apply()
    }
}
