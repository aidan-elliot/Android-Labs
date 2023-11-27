package com.cst3104.id41080471

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import java.util.concurrent.Executors
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatRoom : AppCompatActivity() {
    private lateinit var db: MessageDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        // Initialize the database asynchronously
        lifecycleScope.launch(Dispatchers.IO) {
            db = Room.databaseBuilder(
                applicationContext,
                MessageDatabase::class.java, "message-database"
            ).build()
        }
    }

    fun deleteMessage(messageData: MessageData) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.chatMessageDao().deleteMessage(messageData)
        }
    }
}

