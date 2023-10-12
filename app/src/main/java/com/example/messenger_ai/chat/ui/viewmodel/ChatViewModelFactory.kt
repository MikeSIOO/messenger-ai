package com.example.messenger_ai.chat.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger_ai.chat.domain.ChatRepository
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ChatViewModelFactory @AssistedInject constructor(
    private val chatRepository: ChatRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(chatRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    @AssistedFactory
    interface Factory {
        fun create(): ChatViewModelFactory
    }
}
