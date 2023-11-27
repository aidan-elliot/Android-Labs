package com.cst3104.id41080471

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.room.Room
import java.util.concurrent.Executors

class ChatRoom : AppCompatActivity() {
    private lateinit var db: MessageDatabase
    private val dbExecutor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        db = Room.databaseBuilder(
            applicationContext,
            MessageDatabase::class.java, "message-database"
        ).build()
    }
    fun deleteMessage(messageData: MessageData) {
        dbExecutor.execute {
            db.chatMessageDao().deleteMessage(messageData)
        }
    }
}

