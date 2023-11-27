package com.cst3104.id41080471

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageData::class], version = 2)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDAO
}
