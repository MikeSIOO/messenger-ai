package com.example.messenger_ai.dialogues.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dialogues")
data class DialogueEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "time") val time: String
)
