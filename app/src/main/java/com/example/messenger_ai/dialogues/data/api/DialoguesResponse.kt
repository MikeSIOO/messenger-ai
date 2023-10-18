package com.example.messenger_ai.dialogues.data.api

data class DialoguesResponse(
    val results: ArrayList<Dialogue> = arrayListOf(),
) {
    data class Dialogue(
        val id: Int? = null,
        val avatar: String = "",
        val name: String = "",
        val message: String = "",
        val time: String = ""
    )
}
