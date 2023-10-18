package com.example.messenger_ai.dialogues.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DialoguesDao {
    @Query("SELECT * FROM dialogues")
    fun getDialogues(): List<DialogueEntity>

    @Insert
    fun insert(dialogueEntity: DialogueEntity)

    @Delete
    fun delete(dialogueEntity: DialogueEntity)
}
