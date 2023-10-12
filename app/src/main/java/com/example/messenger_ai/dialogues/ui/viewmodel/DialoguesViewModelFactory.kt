package com.example.messenger_ai.dialogues.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messenger_ai.dialogues.domain.DialoguesRepository
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DialoguesViewModelFactory @AssistedInject constructor(
    private val dialoguesRepository: DialoguesRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DialoguesViewModel::class.java)) {
            return DialoguesViewModel(dialoguesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    @AssistedFactory
    interface Factory {
        fun create(): DialoguesViewModelFactory
    }
}
