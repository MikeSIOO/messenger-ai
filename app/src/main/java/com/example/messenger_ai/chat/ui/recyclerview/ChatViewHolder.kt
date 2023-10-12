package com.example.messenger_ai.chat.ui.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.messenger_ai.R
import com.example.messenger_ai.chat.data.api.ChatResponse

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val message: TextView = itemView.findViewById(R.id.message)
    private val time: TextView = itemView.findViewById(R.id.time)

    fun bind(message: ChatResponse.Message) {
        this.message.text = message.message
        time.text = message.time
    }
}
