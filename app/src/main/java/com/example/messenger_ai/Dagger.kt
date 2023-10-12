package com.example.messenger_ai

import com.example.messenger_ai.chat.data.repository.ChatRepositoryImpl
import com.example.messenger_ai.chat.domain.ChatRepository
import com.example.messenger_ai.chat.ui.ChatFragment
import com.example.messenger_ai.dialogues.data.repository.DialoguesRepositoryImpl
import com.example.messenger_ai.dialogues.domain.DialoguesRepository
import com.example.messenger_ai.dialogues.ui.DialoguesFragment
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: DialoguesFragment)
    fun inject(fragment: ChatFragment)
}

@Module(includes = [AppBindModule::class])
class AppModule

@Module
interface AppBindModule {
    @Binds
    fun bindDialoguesRepository(
        dialoguesRepositoryImpl: DialoguesRepositoryImpl
    ): DialoguesRepository

    @Binds
    fun bindChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository
}
