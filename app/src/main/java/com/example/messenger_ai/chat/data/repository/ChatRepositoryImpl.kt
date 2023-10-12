package com.example.messenger_ai.chat.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.messenger_ai.chat.data.api.ChatFakeService
import com.example.messenger_ai.chat.data.api.ChatResponse
import com.example.messenger_ai.chat.domain.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatFakeService
) : ChatRepository {
    override suspend fun getMessages(dialogueId: Int): LiveData<PagingData<ChatResponse.Message>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 3
            ),
            pagingSourceFactory = {
                ChatPagingSource(chatService, dialogueId)
            },
            initialKey = 1
        ).liveData
    }
}
