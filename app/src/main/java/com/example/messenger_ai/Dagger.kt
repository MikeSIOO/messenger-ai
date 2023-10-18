package com.example.messenger_ai

import android.content.Context
import androidx.room.Room
import com.example.messenger_ai.chat.data.repository.ChatRepositoryImpl
import com.example.messenger_ai.chat.domain.ChatRepository
import com.example.messenger_ai.chat.ui.ChatFragment
import com.example.messenger_ai.dialogues.data.db.DialoguesDao
import com.example.messenger_ai.dialogues.data.db.DialoguesDatabase
import com.example.messenger_ai.dialogues.data.repository.DialoguesRepositoryImpl
import com.example.messenger_ai.dialogues.domain.DialoguesRepository
import com.example.messenger_ai.dialogues.ui.DialoguesFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: DialoguesFragment)
    fun inject(fragment: ChatFragment)
}

@Module(includes = [DbModule::class, AppBindModule::class])
class AppModule

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDialoguesDatabase(context: Context): DialoguesDatabase {
        return Room.databaseBuilder(
            context,
            DialoguesDatabase::class.java,
            "dialogues"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDialoguesDao(dialoguesDatabase: DialoguesDatabase): DialoguesDao {
        return dialoguesDatabase.dialoguesDao()
    }
}

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
