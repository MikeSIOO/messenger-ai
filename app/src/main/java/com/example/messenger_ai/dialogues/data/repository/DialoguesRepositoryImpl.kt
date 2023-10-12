package com.example.messenger_ai.dialogues.data.repository

import com.example.messenger_ai.dialogues.data.api.DialoguesFakeService
import com.example.messenger_ai.dialogues.data.api.DialoguesResponse
import com.example.messenger_ai.dialogues.domain.DialoguesRepository
import javax.inject.Inject

class DialoguesRepositoryImpl @Inject constructor(
    private val dialoguesService: DialoguesFakeService
) : DialoguesRepository {
    override suspend fun getDialogues(): DialoguesResponse {
        return dialoguesService.getDialogues()
    }
}
