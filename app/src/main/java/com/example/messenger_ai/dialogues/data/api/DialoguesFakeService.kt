package com.example.messenger_ai.dialogues.data.api

import kotlinx.coroutines.delay
import javax.inject.Inject

class DialoguesFakeService @Inject constructor() {
    private val dialoguesResponse = DialoguesResponse(
        arrayListOf(
            DialoguesResponse.Dialogue(
                0,
                "https://www.techrepublic.com/wp-content/uploads/2023/07/tr71123-ai-art.jpeg",
                "Name",
                "Message",
                "11:11"
            ),
            DialoguesResponse.Dialogue(
                1,
                "https://www.techrepublic.com/wp-content/uploads/2023/07/tr71123-ai-art.jpeg",
                "ChatGPT",
                "Message\nMessage\nMessage",
                "11.09.21"
            ),
            DialoguesResponse.Dialogue(
                2,
                "https://www.techrepublic.com/wp-content/uploads/2023/07/tr71123-ai-art.jpeg",
                "NameNameNameNameNameNameNameNameNameNameNameNameNameNameName",
                "MessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessage",
                "13 авг"
            ),
            DialoguesResponse.Dialogue(
                3,
                "https://www.techrepublic.com/wp-content/uploads/2023/07/tr71123-ai-art.jpeg",
                "Name\nName",
                "Message",
                "23:45"
            ),
        )
    )

    suspend fun getDialogues(): DialoguesResponse {
        delay(1000)
        return dialoguesResponse
    }
}
