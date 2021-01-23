package com.example.tasks.view.di

import android.content.Context
import androidx.room.Room
import com.example.tasks.service.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PersonRepositoryModule {
    @Provides
    @Singleton
    fun providePersonRepository(@ApplicationContext context: Context):PersonRepository{
        return PersonRepository(context)
    }
}