package com.example.messenger_ai.dialogues.domain

import com.example.messenger_ai.dialogues.data.DialogueModel

interface DialoguesRepository {
    suspend fun getDialogues(): List<DialogueModel>
}
