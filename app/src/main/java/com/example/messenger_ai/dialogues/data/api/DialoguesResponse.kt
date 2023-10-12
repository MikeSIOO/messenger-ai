package com.example.messenger_ai.dialogues.data.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class DialoguesResponse(
    val results: ArrayList<Dialogue> = arrayListOf(),
) {
    @Parcelize
    data class Dialogue(
        val id: Int? = null,
        val avatar: String = "",
        val name: String = "",
        val message: String = "",
        val time: String = ""
    ) : Parcelable
}
