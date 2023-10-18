package com.example.messenger_ai.dialogues.data

import com.example.messenger_ai.dialogues.data.api.DialoguesResponse
import com.example.messenger_ai.dialogues.data.db.DialogueEntity

fun DialoguesResponse.Dialogue.toModel(): DialogueModel {
    return DialogueModel(
        this.id,
        this.avatar,
        this.name,
        this.message,
        this.time,
    )
}

fun DialogueEntity.toModel(): DialogueModel {
    return DialogueModel(
        this.id,
        "",
        this.name,
        this.message,
        this.time,
    )
}

fun DialoguesResponse.Dialogue.toDb(): DialogueEntity {
    if (this.id != null) {
        return DialogueEntity(
            this.id,
            this.name,
            this.message,
            this.time,
        )
    } else {
        throw Exception("Dialogue id is null")
    }
}
