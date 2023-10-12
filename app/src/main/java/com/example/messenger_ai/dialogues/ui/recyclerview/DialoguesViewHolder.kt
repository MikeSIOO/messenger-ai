package com.example.messenger_ai.dialogues.ui.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.messenger_ai.R
import com.example.messenger_ai.dialogues.data.api.DialoguesResponse

class DialoguesViewHolder(
    itemView: View,
    private val callback: (dialogue: DialoguesResponse.Dialogue) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val context by lazy { itemView.context }

    private val avatar: ImageView = itemView.findViewById(R.id.avatar)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val message: TextView = itemView.findViewById(R.id.message)
    private val time: TextView = itemView.findViewById(R.id.time)

    fun bind(dialogue: DialoguesResponse.Dialogue) {
        Glide
            .with(context)
            .load(dialogue.avatar)
            .into(avatar)

        name.text = dialogue.name
        message.text = dialogue.message
        time.text = dialogue.time

        itemView.setOnClickListener {
            callback(dialogue)
        }
    }
}
