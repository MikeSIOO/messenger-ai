package com.example.messenger_ai.dialogues.data.repository

import com.example.messenger_ai.dialogues.data.DialogueModel
import com.example.messenger_ai.dialogues.data.api.DialoguesFakeService
import com.example.messenger_ai.dialogues.data.api.DialoguesResponse
import com.example.messenger_ai.dialogues.data.toModel
import com.example.messenger_ai.dialogues.data.db.DialoguesDao
import com.example.messenger_ai.dialogues.data.toDb
import com.example.messenger_ai.dialogues.domain.DialoguesRepository
import javax.inject.Inject

class DialoguesRepositoryImpl @Inject constructor(
    private val dialoguesService: DialoguesFakeService,
    private val dialoguesDao: DialoguesDao,
) : DialoguesRepository {
    override suspend fun getDialogues(): List<DialogueModel> {
        lateinit var remoteData: DialoguesResponse
        val localData = dialoguesDao.getDialogues()

        try {
            remoteData = dialoguesService.getDialogues()
        } catch (throwable: Throwable) {
            return localData.map { it.toModel() }
        }

        localData.forEach { dialoguesDao.delete(it) }
        remoteData.results.forEach { dialoguesDao.insert(it.toDb()) }

        return remoteData.results.map { it.toModel() }
    }
}
