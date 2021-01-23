package com.example.tasks.view.di.register

import android.app.Application
import com.example.tasks.service.usecase.CreateUseCase
import com.example.tasks.service.usecase.LoginUseCase
import com.example.tasks.view.login.LoginViewModelFactory
import com.example.tasks.view.register.RegisterViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class RegisterModule () {
   @Provides
   fun provideRegisterViewModelFactory(
        application : Application,
        createUseCase: CreateUseCase
    ): RegisterViewModelFactory {
        return RegisterViewModelFactory(
            application,
            createUseCase
        )
    }


}