package com.example.messenger_ai.chat.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.messenger_ai.chat.data.api.ChatResponse

interface ChatRepository {
    suspend fun getMessages(dialogueId: Int): LiveData<PagingData<ChatResponse.Message>>
}
