package com.example.messenger_ai.dialogues.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.messenger_ai.R
import com.example.messenger_ai.dialogues.data.api.DialoguesResponse

class DialoguesAdapter(private val callback: (dialogue: DialoguesResponse.Dialogue) -> Unit) :
    Adapter<DialoguesViewHolder>() {
    var dialogues: ArrayList<DialoguesResponse.Dialogue> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialoguesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return DialoguesViewHolder(
            layoutInflater.inflate(
                R.layout.chat_preview,
                parent,
                false
            ),
            callback
        )
    }

    override fun getItemCount() = dialogues.size
    override fun onBindViewHolder(holder: DialoguesViewHolder, position: Int) {
        holder.bind(dialogues[position])
    }
}
