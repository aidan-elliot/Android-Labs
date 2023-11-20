package com.cst3104.id41080471

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "isReceived") val isReceived: Boolean
)

