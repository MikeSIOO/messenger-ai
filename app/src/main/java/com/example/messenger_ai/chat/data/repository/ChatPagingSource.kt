package com.example.messenger_ai.chat.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.messenger_ai.chat.data.api.ChatFakeService
import com.example.messenger_ai.chat.data.api.ChatResponse
import java.io.IOException

class ChatPagingSource(
    private val chatService: ChatFakeService,
    private val dialogueId: Int
) : PagingSource<Int, ChatResponse.Message>() {
    override fun getRefreshKey(state: PagingState<Int, ChatResponse.Message>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatResponse.Message> {
        return try {
            val page = params.key ?: 1

            val response = chatService.getMessages(page, dialogueId)

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (response.messages.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = response.messages,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
