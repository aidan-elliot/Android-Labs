package com.cst3104.id41080471

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Main : AppCompatActivity() {
    companion object {
        const val DATE_FORMAT = "yyyyMMdd_HHmmss"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.sendButton)
        val messageInput = findViewById<EditText>(R.id.enterMessage)
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)

        val messages = mutableListOf<MessageData>()
        val adapter = MyAdapter(messages, applicationContext)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString()
            if (messageText.isNotEmpty()) {
                val dateAndTime = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
                messages.add(MessageData(messageText, dateAndTime))
                messageInput.text.clear()
                adapter.notifyItemInserted(messages.size - 1)
            }
        }
    }
}
