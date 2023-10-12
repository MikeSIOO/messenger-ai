package com.example.messenger_ai.dialogues.domain

import com.example.messenger_ai.dialogues.data.api.DialoguesResponse

interface DialoguesRepository {
    suspend fun getDialogues(): DialoguesResponse
}
