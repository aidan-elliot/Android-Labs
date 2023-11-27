package com.cst3104.id41080471
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatMessageDAO {
    @Insert
    suspend fun insertMessage(messageData: MessageData)

    @Query("SELECT * FROM messages")
    suspend fun getAllMessages(): List<MessageData>

    @Delete
    suspend fun deleteMessage(messageData: MessageData)
}