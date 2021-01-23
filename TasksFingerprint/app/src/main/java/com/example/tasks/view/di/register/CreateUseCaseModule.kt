package com.example.tasks.view.di.register

import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.usecase.CreateUseCase
import com.example.tasks.service.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class CreateUseCaseModule {

    @Provides
    fun provideCreateUseCase(personRepository: PersonRepository):CreateUseCase{
        return CreateUseCase(personRepository)
    }
}