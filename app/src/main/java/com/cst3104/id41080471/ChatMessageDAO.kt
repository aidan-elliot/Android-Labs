package com.cst3104.id41080471
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatMessageDAO {
    @Insert
    fun insertMessage(messageData: MessageData)

    @Query("SELECT * FROM messages")
    fun getAllMessages(): List<MessageData>

    @Delete
    fun deleteMessage(messageData: MessageData)
}