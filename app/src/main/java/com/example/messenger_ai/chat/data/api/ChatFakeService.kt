package com.example.messenger_ai.chat.data.api

import kotlinx.coroutines.delay
import javax.inject.Inject

class ChatFakeService @Inject constructor() {
    suspend fun getMessages(page: Int = 1, dialogueId: Int): ChatResponse {
        val pageSize = 20
        delay(1000)
        val messages = arrayListOf<ChatResponse.Message>()
        for (i in (page - 1) * pageSize..<page * pageSize) {
            messages.add(
                ChatResponse.Message(
                    i,
                    i % 3 == 0,
                    if (i % 2 == 0) "Message #$i\nDialogue #$dialogueId" else "Loooooooooooooooooong #$i",
                    "11:11"
                )
            )
        }
        return ChatResponse(
            messages
        )
    }
}
