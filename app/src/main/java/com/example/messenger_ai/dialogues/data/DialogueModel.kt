package com.example.messenger_ai.dialogues.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogueModel(
    val id: Int? = null,
    val avatar: String = "",
    val name: String = "",
    val message: String = "",
    val time: String = ""
) : Parcelable
