package com.example.messenger_ai.chat.data.api

data class ChatResponse(
    val messages: ArrayList<Message> = arrayListOf()
) {
    data class Message(
        val id: Int? = null,
        val isMine: Boolean = true,
        val message: String = "",
        val time: String = ""
    )
}
