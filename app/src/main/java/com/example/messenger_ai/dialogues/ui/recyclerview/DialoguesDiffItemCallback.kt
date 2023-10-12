package com.example.messenger_ai.dialogues.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.messenger_ai.dialogues.data.api.DialoguesResponse

class DialoguesDiffItemCallback : DiffUtil.ItemCallback<DialoguesResponse.Dialogue>() {
    override fun areItemsTheSame(
        oldItem: DialoguesResponse.Dialogue,
        newItem: DialoguesResponse.Dialogue
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DialoguesResponse.Dialogue,
        newItem: DialoguesResponse.Dialogue
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
