package com.example.messenger_ai.dialogues.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DialogueEntity::class], version = 1)
abstract class DialoguesDatabase : RoomDatabase() {
    abstract fun dialoguesDao(): DialoguesDao
}
