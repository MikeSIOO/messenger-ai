package com.example.messenger_ai.chat.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.messenger_ai.chat.data.api.ChatResponse
import com.example.messenger_ai.chat.domain.ChatRepository

class ChatViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {
    suspend fun getMessages(dialogueId: Int): LiveData<PagingData<ChatResponse.Message>> {
        return chatRepository.getMessages(dialogueId).cachedIn(viewModelScope)
    }
}
