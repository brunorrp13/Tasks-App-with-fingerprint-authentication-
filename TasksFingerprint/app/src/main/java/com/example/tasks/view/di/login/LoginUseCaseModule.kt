package com.example.tasks.view.di.login

import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class LoginUseCaseModule {

    @Provides
    fun provideLoginUseCase(personRepository: PersonRepository):LoginUseCase{
        return LoginUseCase(personRepository)
    }
}