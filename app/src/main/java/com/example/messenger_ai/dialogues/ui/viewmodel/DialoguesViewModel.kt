package com.example.messenger_ai.dialogues.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messenger_ai.utils.Status
import com.example.messenger_ai.dialogues.data.api.DialoguesResponse
import com.example.messenger_ai.dialogues.domain.DialoguesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DialoguesViewModel(
    private val dialoguesRepository: DialoguesRepository
) : ViewModel() {
    private val _dialogues: MutableLiveData<DialoguesResponse> =
        MutableLiveData(DialoguesResponse())
    val dialogues: LiveData<DialoguesResponse> = _dialogues

    private val _status: MutableLiveData<Status> = MutableLiveData(Status.LOADING)
    val status: LiveData<Status> = _status

    fun getDialogues() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                val result = withContext(Dispatchers.IO) {
                    dialoguesRepository.getDialogues()
                }
                _dialogues.value = result
                _status.value = Status.SUCCESS
            } catch (error: Throwable) {
                _status.value = Status.ERROR
            }
        }
    }
}
