package com.example.messenger_ai.chat.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.messenger_ai.R
import com.example.messenger_ai.chat.data.api.ChatResponse

class ChatAdapter :
    PagingDataAdapter<ChatResponse.Message, ChatViewHolder>(ChatDiffItemCallback()) {
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = if (viewType == R.layout.message_mine) {
            LayoutInflater.from(parent.context).inflate(R.layout.message_mine, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.message_companion, parent, false)
        }
        return ChatViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val layout = if (getItem(position)?.isMine == true) {
            R.layout.message_mine
        } else {
            R.layout.message_companion
        }
        return layout
    }
}
