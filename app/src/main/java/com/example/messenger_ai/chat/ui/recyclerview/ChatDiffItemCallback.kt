package com.example.messenger_ai.chat.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.messenger_ai.chat.data.api.ChatResponse

class ChatDiffItemCallback : DiffUtil.ItemCallback<ChatResponse.Message>() {
    override fun areItemsTheSame(
        oldItem: ChatResponse.Message,
        newItem: ChatResponse.Message
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ChatResponse.Message,
        newItem: ChatResponse.Message
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
